#!/bin/sh
chmod 740 /root/.ssh/authorized_keys &&
chmod 700 /root/.ssh &&
chmod 700 /root/ &&
nohup /usr/sbin/sshd -D -e &
cd /back && gradle bootJar && java -jar build/libs/server-1.0.jar