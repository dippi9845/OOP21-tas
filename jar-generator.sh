#!/bin/bash
COMPILED=`find bin/ -name "*.class" 2>/dev/null | wc -l`
if [[ ! -d bin/ || $COMPILED == "0" ]]; then
	echo "No compilation found, will be run"
	./compile.sh
fi

LIBS=`find lib/ -type f -name "*.jar"`
for i in $LIBS; do
	unzip $i "*.class" -d bin/
done
jar -cfm TowerAndStuff.jar MANIFEST.MF res/ -C bin/ .
