#!/bin/bash

#remove exist files
rm -rf ./src/*.class
rm -rf ./output/sessionization.txt

javac ./src/*.java
java  -classpath ./src sessionization ./input/log.csv ./input/inactivity_period.txt ./output/sessionization.txt

