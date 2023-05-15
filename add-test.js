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
    return [argv[2], argv[3]]
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

const makeTestTemplateFile = async ( file, package, className) =>{
    const contents = `
package ${package};

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 자바 MockMvc 경고 무시
public class ${className} {

    @Autowired
    private MockMvc mvc;

    @Test
    void test() throws Exception {

    }
}
    `
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
    const [CLASS_PATH, DEBUG_OPT] = initOptions(argv);
    
    if( DEBUG_OPT ){
        test( 
            () => verifyClassPath("a.b.c.d/test"),
            () => parseClassPath("a.b.c.d.Test.java")
        )
    }

    if( !verifyClassPath( CLASS_PATH ) ){
        printInvalidClassPath( CLASS_PATH )
        return;
    }

    const parseResult = parseClassPath(CLASS_PATH);
    if( !parseResult ){
        return;
    }
    
    const { fileName, filePath, dirDeps, package, className } = parseResult;
    
    if ( !await mkdirs(dirDeps) ){
        return;
    }

    await makeTestTemplateFile(path.join(filePath, fileName), package, className);
    
};


main(process.argv);