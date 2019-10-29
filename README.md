
#### Requirements

    PostgreSQL 9.4
    Java 1.8
    Maven

###### How to deploy
file properties:

    src\main\resources\application.properties
change config:

    spring.datasource.url=jdbc:postgresql://localhost:5432/reservation
    spring.datasource.username=postgres
    spring.datasource.password=root

restore db:
```
psql -U user dbase < src\main\resources\db\migration\postgresql\V1_dump.sql
```
in root directory:
```
mvn package
java -jar reservation-1.0-SNAPSHOT.jar
```

