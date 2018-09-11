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


rm -fr /Users/yye/apache-tomcat-9.0.12/webapps/*
cp target/*.war /Users/yye/apache-tomcat-9.0.12/webapps/

echo --------------------------
echo Moved WAR FILE for UTR
echo --------------------------

echo --------------------------
echo DONE WITH DEPLOY TASK
echo --------------------------


