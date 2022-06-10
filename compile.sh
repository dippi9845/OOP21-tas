#!/bin/bash
javac -cp bin:. -d bin $(find src/main/ -name "*.java" )
