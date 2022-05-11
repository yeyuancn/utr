#!/bin/bash
clear

echo --------------------------
echo Start UTR Master build
echo --------------------------


mvn clean install
echo --------------------------
echo DONE WITH Build Tennis
echo --------------------------

echo --------------------------
echo Deploy the WAR files
echo --------------------------


rm -fr /Users/yuan.ye/apache-tomcat-8.5.78/webapps/utr*
cp target/utr.war /Users/yuan.ye/apache-tomcat-8.5.78/webapps/

echo --------------------------
echo Moved WAR FILE for UTR
echo --------------------------

echo --------------------------
echo DONE WITH DEPLOY TASK
echo --------------------------


