# invoice

[Swagger API documentation](https://migreylopez.github.io/invoice/)

## Local deployment

Build the shadowJar:
```
./gradlew clean build shadowJar
```
Start the service & database through docker-compose and create the DynamoDB table:
```
make start
```
Once you're finished the service & database can be stopped by:
```
make stop
```