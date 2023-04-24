# Drones Management App
Drone App is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/).

### Prerequisites
The following items should be installed in your system:
* Java 11 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Docker Container to run the Database
* Makefile. it is optional


## Database configuration
For the default database we used the postgres docker container and for the test we use h2 in memory database

To run in locally first we need to configure the database. We used the postgres docker container to run the application

To run the db follow the below command

Using Docker command
``` dockerfile
docker-compose-up
```

or Using Makefile

```
make infra
```


### To Run locally
You can build a jar file and run it from the command line:

```
https://github.com/BiranSyangbo/drone-app.git
cd drone-app
./mvnw package
java -jar target/*.jar
```

You can then access Dron App Api here: [http://localhost:8090/](http://localhost:8090/)

Or you can run it from Maven directly using the Spring Boot Maven plugin.

```
./mvnw spring-boot:run
```


#### Details

To build the application we used the spring boot 3.0.6 version and postgres for the database and JPA for communication with db.


To insert the data please use file ``` drone-app-request.http```
