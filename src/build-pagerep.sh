#!/bin/bash

clear

echo -e "Compiling..."
javac PageRep/*.java

echo -e "\nRunning PageRep using input1...\n"
java PageRep.Main < PageRep/input-list.txt

#echo -e "\nRunning PageRep using input2...\n"
#java PageRep.Main < input2
