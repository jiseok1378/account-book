#!/bin/sh

nohup /usr/sbin/sshd -D -e &

cd /back && gradle bootJar && java -jar build/libs/server-1.0.jar