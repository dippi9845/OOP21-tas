#!/bin/bash
LIBS=`find lib/ -name "*.jar"`
for i in $LIBS; do
        unzip -u $i -d bin/
done
find bin/ -type f ! -name "*.class" -delete
find bin/ -type d -empty -delete
