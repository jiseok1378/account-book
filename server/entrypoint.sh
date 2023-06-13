#!/bin/sh
chmod 740 /root/.ssh/authorized_keys &&
chmod 700 /root/.ssh &&
chmod 700 /root/ &&
nohup /usr/sbin/sshd -D -e &
if [ -z $VERSION ]
then
    java -jar /server/server.jar
else 
    java -jar /server/server.jar
fi

