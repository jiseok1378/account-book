#!/bin/sh

nohup /usr/sbin/sshd -D -e &
envsubst '$${WEB_PORT}' < /etc/nginx/conf.d/default-template > /etc/nginx/conf.d/default.conf &&  nginx -g 'daemon off;'