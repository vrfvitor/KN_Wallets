#!/usr/bin/env bash

cd backend
mvn clean package -DskipTests
docker build -t vrfvitor/knwallets:latest -f Dockerfile .

cd ../frontend
docker build -t vrfvitor/knwallets-spa:latest -f Dockerfile .