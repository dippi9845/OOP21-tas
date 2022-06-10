#!/bin/bash
./lib-upacker.sh
javac -cp bin/:. -d bin $(find src/main/ -name "*.java" )
