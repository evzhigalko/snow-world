#!/bin/sh
mvn clean package
cp ~/dev/pet-project-zhigalko/web/target/snow-world.war /opt/apache-tomcat/9.0.59/webapps/snow-world.war
