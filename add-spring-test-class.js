const fs = require('fs');
const os = require('os');
const path = require('path');

const serverTestDirPath = [".", "server", "src", "test", "java"]

let isValidOption = true;

const parseOption = ( argv ) => {

    const opt = [
        { 
            long: 'class',
            short: 'c',
            require: true,
            description: 'Input java class path'
        },
        {
            long: 'debug',
            short: 'd',
            description: 'Debug',
            isBool : true,
        },
        {
            long: 'name',
            short: 'n',
            description: 'Input test class title'
        }
    ];
    argv.forEach((arg, index) => {
        if( arg.startsWith("-") ){
            const option = arg.replace(/-/gi, '');
            const target = opt.find((value) => value.long == option || value.short == option)
            
            if( target == undefined ){
                console.error(`Invalid option: ${arg}`)
                isValidOption = false;
                return;
            }
            
            if( target.isBool ){
                target.data = true;
            }
            else {
                target.data = argv[index + 1]
            }
        }
    });

    const noDataOptions = opt.filter((x) => x.require && !x.data )
    if(noDataOptions.length != 0){
        console.error(`Required Option: [ ${noDataOptions.map(x => `--${x.long} ${x.short ? `| -${x.short}` : ''}`).join(" , ")} ]`)
        isValidOption = false;
    }
            

    return opt;
}


const getOptionData = (argv) => {
    const map = {};
    OPTIONS.forEach( x => map[x.long] = x.data);
    return map
}

const printUseage = () => {
    const msg = `
Usage : node ${path.basename(__filename)} [OPTIONS...]

Options:
    ${OPTIONS.map(x => ( x.description ? `\t[ --${x.long} ${x.short ? `| -${x.short}`: ``} ] \t${x.description} ${x.require ? '[Require]' : ''}` : "" )).join("\n")}
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

const parseClassPath = ( classPath ) => {
    const classPathArray = classPath.split(".");
    
    const dirArray = classPathArray.slice(0, classPathArray.length - 1);

    const fileName = `${classPathArray[classPathArray.length - 1]}.java`;
    
    const filePath = path.join(...[...serverTestDirPath,...dirArray])
    
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

const DEBUG = OPTIONS.find(x =>x.long == 'debug').data;

const main = async () => {
    if( !isValidOption ){
        printUseage();
        return;
    }
    
    if( DEBUG ){
        console.log(OPTIONS)
    }

    const options = getOptionData();
    
    const classPath = options["class"]

    const { name } = options
    
    if( !verifyClassPath( classPath ) ){
        return;
    }

    const parseResult = parseClassPath(classPath);
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