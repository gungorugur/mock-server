# Mock-Server

Mock server were built on top of WireMock. 
Implement MockService interface and write WireMock rules in the start method. 
You are ready to deploy your fake endpoints!  
I've added examples of get resource  and post resource. You can easily  add your mock services by looking through to usermockservice and productmockservice examples.
If you are need more examples just check WireMock docs: 
http://wiremock.org/docs/
http://wiremock.org/docs/stubbing/
http://wiremock.org/docs/request-matching/

### Build

```
./gradlew clean build
```


### Run


```
./gradlew clean fatJar
```

```
java -jar build/libs/mock-server-1.0-SNAPSHOT.jar 
```

## WireMock admin

You can get information about mock endpoints you've declared.
Go to http://localhost:8080/__admin/

## Example Endpoints

Get Products

```
curl -X GET http://localhost:8080/product-service/products
```

Get User By Id

```
curl -X GET http://localhost:8080/user-service/users/aaf45f4d0e6c422c80d55417c1ac2725
```

Post User

```
curl -X POST \
  http://localhost:8080/user-service/users \
  -H 'Content-Type: application/json' \
  -d '{
	"name":"ugur",
	"surname":"gungor"
}'
```
