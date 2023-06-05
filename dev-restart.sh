#!/bin/bash

APP_SERVER_NAME=app-server-dev
APP_WEB_NAME=app-web-dev
TARGET=$1
IS_REBUILD=$2


function rebuild(){
    if [ $IS_REBUILD = 'build' ]
    then
        bash docker-compose-prd build
    fi
}

function restartContainer(){
    if [ $TARGET = 'web' ] || [ $TARGET = 'w' ] 
    then 
        docker restart $APP_WEB_NAME 
    elif [ $TARGET = 'server' ] || [ $TARGET = 's' ] 
    then
        docker restart $APP_SERVER_NAME 
    fi
}


if [ -z $TARGET ]
then
    echo "USEAGE: $0 [web | server]"
else
    rebuild &&
    restartContainer
fi 


