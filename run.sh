#!/bin/bash

#remove exist files
rm -rf ./src/sessionization.class
rm -rf ./output/sessionization.txt

javac ./src/sessionization.java
java  -classpath ./src sessionization ./input/log.csv ./input/inactivity_period.txt ./output/sessionization.txt

