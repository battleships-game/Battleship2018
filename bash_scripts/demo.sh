#!/bin/bash

wget http://46.41.138.105/client-0.5.jar client-0.5.jar
fuser -k 8082/tcp 
nohup java -jar client-0.5.jar &
sleep 10
x-www-browser localhost:8082
