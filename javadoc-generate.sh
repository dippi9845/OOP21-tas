#!/bin/bash
./lib-upacker.sh
javadoc -cp bin/ -d "./doc/" -sourcepath "./src/" -subpackages main.java.tas
echo -n "Do you want to generate the jar with javadoc ? [N/y] "
read choose
if [[ $choose == "y" ]]; then
	jar -cf javadoc.jar doc/
	rm -r doc
fi
