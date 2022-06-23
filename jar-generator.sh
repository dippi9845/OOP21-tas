#!/bin/bash
COMPILED=`find bin/ -name "*.class" 2>/dev/null | wc -l`

if [[ ! -d bin/ || $COMPILED == "0" ]]; then
	echo "No compilation found, will be run"
	./compile.sh
fi

cp -r res/* bin/
jar -cfm TowerAndStuff.jar MANIFEST.MF res/ -C bin/ .
ADDEDDIRS=`find res/ -mindepth 1 -maxdepth 1 -type d`

for i in $ADDEDDIRS; do
        rm -r ${i/res/bin} 
done
