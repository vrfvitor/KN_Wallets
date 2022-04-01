#!/usr/bin/env bash

mvn clean package -DskipTests
docker build -t vrfvitor/knwallets:latest -f Dockerfile .