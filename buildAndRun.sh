#!/bin/bash
#remove all built
rm -fR ./build
#build all
find . -name "*.java" -print | xargs javac -d ./build
#run main
java -cp ./build main.MainClass

