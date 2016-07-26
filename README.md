## Docker
mvn package docker:build

docker run -p 8085:8085 -t cooleo/coole-hbase-phoenix-service