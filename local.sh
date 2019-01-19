#!/bin/sh
mvn package
cd target
chmod 777 app-tests.jar
cp app-tests.jar ../
java -jar app-tests.jar