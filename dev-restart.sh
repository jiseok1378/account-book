#!/bin/bash

APP_SERVER_NAME=app-server-dev
APP_WEB_NAME=app-web-dev
TARGET=$1

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
    restartContainer
fi 


