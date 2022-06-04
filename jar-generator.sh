#!/bin/bash
$COMPILED=`find bin/ -name "*.class" 2>/dev/null | wc -l`
if [[ ! -d bin/ || $COMPILED == "0" ]]; then
	echo "No compilation found, will be run"
	./compile.sh
fi
jar -cfm test.jar MANIFEST.MF res/ -C bin/ .
