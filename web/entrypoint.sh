#!/bin/sh
chmod 740 /root/.ssh/authorized_keys &&
chmod 700 /root/.ssh &&
chmod 700 /root/ &&
nohup /usr/sbin/sshd -D -e &
envsubst '$${WEB_PORT}' < /etc/nginx/conf.d/default-template > /etc/nginx/conf.d/default.conf &&  nginx -g 'daemon off;'