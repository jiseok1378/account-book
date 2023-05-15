const fs = require('fs');
const os = require('os');
const path = require('path');

const serverTestDirPath = [".", "server", "src", "test", "java"]

const verifyArgs = (argv) => {
    if(argv.length <= 5){
        return true;
    }
    else{
        return false;
    }
}

const initOptions = (argv) => {
    const [, , classPath, testName, debug] = argv;
    return {classPath, testName, debug}
}

const printUseage = () => {
    console.log("Usage: node add-test.js [Target Class Path] [debug]")
}

const verifyClassPath = ( classPath ) => {
    const regex = /[\\/]/g
    return !regex.test(classPath);
}

const printInvalidClassPath = ( classPath ) => {
    console.log(`Invalid class path: ${classPath}`)
}

const parseClassPath = ( classPath ) => {
    const classPathArray = classPath.split(".");
    

    const dirArray = classPathArray.slice(0, classPathArray.length - 1);

    const fileName = `${classPathArray[classPathArray.length - 1]}.java`;
    
    const filePath = path.join(...[...serverTestDirPath,...dirArray])
    
    const dirDeps = dirArray.map( (x, idx) => path.join(...[...serverTestDirPath,...dirArray.slice(0, idx), x]))
    
    const package = dirArray.join(".")

    const className = classPathArray[classPathArray.length - 1];

    return { fileName, filePath, dirDeps, package, className };
}

const mkdirs = async (dirDeps) => {
    const loop = async (dirDeps, index = 0) => {
        if( dirDeps.length == index ){
            return true;
        }
        else{
            const targetDir = dirDeps[index]; 
            try{
                if( !(await fs.existsSync(targetDir)) ){
                    await fs.mkdirSync(targetDir);
                }
                return loop(dirDeps, ++index)    
            }
            catch(e){
                console.log(`mkdir error: ${targetDir}`)
                return false;
            }
        }
    }
    return await loop(dirDeps);
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

    if( !(await fs.existsSync(file)) ){
        await fs.writeFileSync(file, contents)
    }
}

const test = ( ...callback ) => {
    callback.map( x => 
        (
            {
                name : x.toString(),
                ret : x.apply() 
            }
        )
    ).forEach( x => console.log(JSON.stringify(x, null, 3)) );
}


const main = async (argv) => {
    if( !verifyArgs(argv) ){
        printUseage();
        return;
    }
    const {classPath, testName, debug} = initOptions(argv);
    
    if( debug ){
        test( 
            () => verifyClassPath("a.b.c.d/test"),
            () => parseClassPath("a.b.c.d.Test.java")
        )
    }

    if( !verifyClassPath( classPath ) ){
        printInvalidClassPath( classPath )
        return;
    }

    const parseResult = parseClassPath(classPath);
    if( !parseResult ){
        return;
    }
    
    const { fileName, filePath, dirDeps, package, className } = parseResult;
    
    if ( !await mkdirs(dirDeps) ){
        return;
    }

    await makeTestTemplateFile(path.join(filePath, fileName), package, className, testName);
    
};


main(process.argv);