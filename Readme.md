# Names-API

This is a small REST-API with partial CRUD functionalities to manage Names.

### About
This project leverage:
- Spring Boot 3
- MySQL
- Java 17 
- Maven
- Docker

Being `Docker` and `MySQL` the absolute baseline to run the project. Otherwise, all the other components should be available on the running machine.

Connection to `MySQL` can be configured through environment variables. And the project expect to find three `DB_URL`, `DB_USER` and `DB_PASS`.

### Endpoints
List of available endpoints (Pointing to [Railway](https://fiducial-names-api-production.up.railway.app/api/names)):
- `GET` [/api/names](https://fiducial-names-api-production.up.railway.app/api/names) 
   - `Parameters`
     - `Query`
       - page: `integer`(optional) default = 0
       - size: `integer` (optional) default = 20

       
- `GET` [/api/exist/{name}](https://fiducial-names-api-production.up.railway.app/api/exist/name)
  - `Parameters`
    - `Path`
      - name: `String`


- `GET` [/api/populate](https://fiducial-names-api-production.up.railway.app/api/populate)
  - `Response`
    - String indicating whether database was populated or not. Will only populated an empty database.


-  `POST` [/api/names](https://fiducial-names-api-production.up.railway.app/api/names)
   - `Parameters`
     - `Body`
       - Set of Strings


- `DELETE` [/api/{name}](https://fiducial-names-api-production.up.railway.app/api/name)
  - `Parameters`
    - `Path`
      - name: String



### Launch 

You can run the spring boot app by typing the following commands:

```
mvn spring-boot:run
```

The server will start on port `8080`.

You can also package the application in the form of a `jar` file and then run it like so:

```
mvn clean package 

java -jar target/Names-1.0.jar
```



