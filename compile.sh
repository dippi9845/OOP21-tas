#!/bin/bash
./lib-unpacker.sh
javac -cp bin/:. -d bin $(find src/main/ -name "*.java" )
