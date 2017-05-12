=======

Backend Services for RMS Application


* Spring Boot
* MySQL
* Apache Maven
* Oauth2
* Jacoco


**Start the application**
```
mwn spring-boot:run
```

**Oauth2 Authorization**
```
// Get token
curl -X POST http://localhost:8000/oauth/token -H "Authorization:Basic Y2xpZW50OnNlY3JldA==" -d "grant_type=password&username=aziz@mitrais.com&password=password"

// Example
curl -X GET http://localhost:8000/api/employee/get-login-user -H "Authorization: Bearer $TOKEN"
```

**Jacoco Code Coverage**
```
mvn clean package
```
Code coverage report for unit tests to the directory target/site/jacoco
