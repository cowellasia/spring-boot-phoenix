# Spring-Boot-Jdbc-Template

JdbcTempalte example using spring boot and embedded database. 

To get list of employees url is : http://hostname:port/api/employees?departmentId=2
	E.g. : http://localhost:8085/api/employees?departmentId=2

## Docker
mvn package docker:build

docker run -p 8085:8085 -t cooleo/coole-hbase-phoenix-service