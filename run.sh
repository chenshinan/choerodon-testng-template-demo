#!/bin/sh
java -jar app-tests.jar
cd test-output
tar -czvf testng-results.tar.gz testng-results.xml
#curl -v -X POST ${RESULTGATEWAY}/test/v1/automation/import/report/mocha?releaseName=${RELEASENAME} -F "file=@$testng-results.tar.gz"
