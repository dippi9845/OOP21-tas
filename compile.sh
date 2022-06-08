#!/bin/bash
javac -cp lib/json-20220320.jar:. -d bin $(find src/main/ -name "*.java" )
