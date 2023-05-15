const fs = require('fs');
const os = require('os');
const path = require('path');

let isValidOption = true;

const parseOption = ( argv ) => {

    const opt = [
        {
            name: 'help',
            long: 'help',
            isBool : true,
            description: 'Print help'
        },
        { 
            name: 'classPath',
            long: 'class',
            short: 'c',
            param: 'class-path',
            require: true,
            description: 'Input java class path'
        },
        {
            name: 'debug', 
            long: 'debug',
            short: 'd',
            description: 'Debug',
            isBool : true,
        },
        {
            name: 'name',
            param: 'class-title',
            long: 'name',
            short: 'n',
            description: 'Input test class title'
        },
        {
            name: 'serverPath',
            param: 'server-path',
            long: 'server',
            short: 's',
            description: 'Server source path',
            default: [".", "server", "src", "test", "java"],
            preprocess: (arg) => {
                return path.normalize(arg).split(path.sep);
            },
            printDefault: (data) => {
                return data.default.join(path.sep)
            }
        }
    ];

    let helpFlag = argv.includes('--help');
    
    opt.forEach((x)=> {
        x.long = "-" + x.long
        if( x.default ){
            x.data = x.default;
        }
    });

    argv.forEach((arg, index) => {
        if( arg.startsWith("-") ){
        
            const option = arg.slice(1, arg.length);

            const target = opt.find((value) => value.long == option || value.short == option)

            if( target == undefined ){
                if(!helpFlag) console.error(`Invalid option: ${arg}`)
                isValidOption = false;
                return;
            }
            
            if( target.isBool ){
                target.data = true;
            }
            else {
                if( target.preprocess ){
                    target.data = target.preprocess(argv[index + 1]);
                }
                else{
                    target.data = argv[index + 1]
                }
            }
        }
    });

    const noDataOptions = opt.filter((x) => x.require && !x.data )

    if(noDataOptions.length != 0){
        if(!helpFlag) console.error(`Required Option: [ ${noDataOptions.map(x => `--${x.long} ${x.short ? `| -${x.short}` : ''}`).join(" , ")} ]`)
        isValidOption = false;
    }
            

    return opt;
}



const getOptionData = (argv) => {
    const map = {};
    OPTIONS.forEach( x => map[x.name] = x.data);
    return map
}

const printUseage = () => {

    const getMaxLongSize = Math.max(...OPTIONS.filter(x=>x.long).map(x=>x.long.length))
    const getMaxShortSize = Math.max(...OPTIONS.filter(x=>x.short).map(x=>x.short.length))

    const makeOptionMsg = (option) => {
        if(option.description){
            const short = option.short ? `| -${option.short} `: ``
            const required = option.require ? '[Require]' : ''
            const param = () => {
                if(!option.isBool){
                    return ` <${option.param ? option.param : 'param'}> `
                }
                else{
                    return ''
                }
            }
            const defalutVal = () => {
                if(option.default){
                    return `[ Default: ${option.printDefault? option.printDefault(option) : option.defult} ]`
                }
                else{
                    return ''
                }
            }
            return `\t${`-${option.long.padEnd(getMaxLongSize, " ")} ${short.padEnd(getMaxShortSize, " ")}${param()}`.padEnd(35, " ")} ${option.description} ${required} ${defalutVal()}`;
        }
        else{
            return ''
        }
    }  
    const msg = `
Usage : node ${path.basename(__filename)} [OPTIONS...]

Options:
    ${OPTIONS.map(makeOptionMsg).join("\n")}
`
    console.log(msg);
}

const verifyClassPath = ( classPath ) => {
    const regex = /[\\/]/g
    const classArray = classPath.split(".")

    if(regex.test(classPath)){
        console.log(`Invalid class path: ${classPath}`)
        return false;
    }
    else if(['java', 'class'].includes(classArray[classArray.length - 1])){
        console.log(`Class names must not include extensions: ${classPath}`)
        return false;
    }
    else{
        return true;
    }
}

const parseClassPath = ( serverPath, classPath ) => {
    const classPathArray = classPath.split(".");
    
    const dirArray = classPathArray.slice(0, classPathArray.length - 1);

    const fileName = `${classPathArray[classPathArray.length - 1]}.java`;
    
    const filePath = path.join(...[...serverPath,...dirArray])
    
    const package = dirArray.join(".")

    const className = classPathArray[classPathArray.length - 1];

    return { fileName, filePath, package, className };
}

const mkdirs = async (filePath) => {

    const loop = async (dirs, nowDir = '', index = 0) => {
        if( dirs.length == index ){
            return true;
        }
        else{
            const targetDir = path.join(nowDir, dirs[index]); 
            try{
                if( !(await fs.existsSync(targetDir)) ){
                    await fs.mkdirSync(targetDir);
                }
                return loop(dirs, targetDir, ++index)    
            }
            catch(e){
                console.log(`mkdir error: ${targetDir}`)
                return false;
            }
        }
    }

    return await loop(filePath.split(path.sep));
}

const makeTestTemplateFile = async ( file, package, className, displayName) =>{
    const getContents = () => {
        const contents = `
package ${package};

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 자바 MockMvc 경고 무시
##DISPLAY_NAME##
public class ${className} {

    @Autowired
    private MockMvc mvc;

    @Test
    void test() throws Exception {

    }
}
        `
        if(displayName === undefined || displayName === null || displayName === ''){
            return contents.replace(/##DISPLAY_NAME##/, '')
        }
        else{
            return contents.replace(/##DISPLAY_NAME##/, `@DisplayName("${displayName}")`)
        }
    }
    const contents = getContents();

    try{
        if( !(await fs.existsSync(file)) ){
            await fs.writeFileSync(file, contents)
            console.log(`SUCCESS: ${file}`)
        }
        else{
            console.log(`EXIST ERROR: ${file}`)
        }    
    }catch(e){
        console.error("=== UNKNOWN ERROR ===")
        console.error(e)
    }
}


const OPTIONS = parseOption(process.argv);
const DEBUG = OPTIONS.find(x =>x.name == 'debug').data;

const main = async () => {

    const { name, serverPath, classPath, help } = getOptionData()

    if( !isValidOption || help ){
        printUseage();
        return;
    }
    
    if( DEBUG ){
        console.log(OPTIONS)
    }
    
    if( !verifyClassPath( classPath ) ){
        return;
    }

    const parseResult = parseClassPath(serverPath, classPath);
    if( !parseResult ){
        return;
    }
    
    const { fileName, filePath, package, className } = parseResult;
    
    if ( !await mkdirs(filePath) ){
        return;
    }

    await makeTestTemplateFile(path.join(filePath, fileName), package, className, name);
    
};


main();