#!/bin/sh
mvn package
cd target
chmod 777 app.jar
cp app.jar ../