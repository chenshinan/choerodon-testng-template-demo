#!/bin/sh
java -jar app-tests.jar
cd test-output
tar -czvf testng-results.tar.gz testng-results.xml
curl -v -X POST localhost:8080/test/v1/automation/import/report/testng?releaseName="testng" -F "file=@testng-results.tar.gz"
