#!/bin/bash

PROJECT_REPO=${HOME}/account-book/web
SSH_OPT='-o StrictHostKeyChecking=no'

cd ${PROJECT_REPO}

function getFrontName(){
    echo app-web-$1
}

function getSSHName(){
    echo root@$(getFrontName $1)
}

function build(){
    echo
    echo '==================== BUILD WEB PROJECT [START] ===================='
    rm -rf ./dist
    yarn install && yarn build
    zip -r dist.zip ./dist
    echo '==================== BUILD WEB PROJECT [END] ===================='
    echo
}

DISTRIBUTION_INNER_VAL=0
function distributionInner(){
    IS_SUCC=''
    i=0
    while [ $? -eq 0  ]
    do
        if [ $i -eq 5 ]
        then
            break
        fi 
        echo checking $(getFrontName $1)...
        IS_SUCC=$(curl -s http://$(getFrontName $1):8$1)
        if [ ! -z "$IS_SUCC" ]
        then
            echo
            echo
            echo '==================== COPY DIST DIRECTORY [START] ===================='  
            scp ${SSH_OPT} -v ./dist.zip $(getSSHName "$1"):/
            echo '==================== COPY DIST DIRECTORY [END] ====================' 
            echo
            echo
            echo '==================== UNZIP DIST DIRECTORY [START] ====================' 
            ssh ${SSH_OPT} $(getSSHName "$1") 'unzip -o /dist.zip -d / && rm /dist.zip && cp -rf /dist/* /user/share/nginx/html && rm -rf /dist'
            echo "Success [$(getFrontName $1)]"
            echo '==================== UNZIP DIST DIRECTORY [END] ====================' 
            echo
            echo
            break
        fi
        ((i++))
        sleep 1
    done
    if [ -z "$IS_SUCC" ]
    then
        DISTRIBUTION_INNER_VAL=-1
        # echo 'FAILED::DISTRIBUTION'
    fi
}

function finally(){
    rm -rf ./dist ./dist.zip 
}

function distribution(){
    CURRENT_IDX=0
    for var in $@
    do
        if [ ! $CURRENT_IDX -eq 0 ] && [ ! $DISTRIBUTION_INNER_VAL -eq 0 ]
        then
            case "$DISTRIBUTION_INNER_VAL" in
                '-1') echo "[$(getFrontName "$CURRENT_IDX")] DISTRIBUTION_FAILED::CONNECT_FAILED";;
            esac
            break;
        fi
        distributionInner "$var"
        ((CURRENT_IDX++))
    done 
}

build
distribution '1' '2'
finally
