## spring-boot-phoenix

A demo how to using Apache Phoenix () with Apache HBase for NoSQL and Bigdata demontration.
This application using SQL interface with NoSQL
Pls reference: http://phoenix.apache.org/
and : http://hbase.apache.org/




## Run & Deployment with Docker
- mvn package docker:build

- docker run -p 8085:8085 -t cooleo/coole-hbase-phoenix-service

