#!/bin/bash
if [[ ! -d bin/ ]]; then
	mkdir bin/
fi
FOLDERS=`find bin/ -mindepth 1 -maxdepth 1 -type d ! -name "test" ! -name "main" | wc -l`
if [[ $FOLDERS == "0" ]]; then
	LIBS=`find lib/ -name "*.jar"`
	for i in $LIBS; do
        	unzip -u $i -d bin/ 1>/dev/null
	done
	find bin/ -type f ! -name "*.class" -delete
	find bin/ -type d -empty -delete
fi
