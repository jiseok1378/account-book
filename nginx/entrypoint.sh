#!/bin/sh

nohup /usr/sbin/sshd -D -e &
nginx -g 'daemon off;'