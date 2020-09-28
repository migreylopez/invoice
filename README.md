# invoice

[Swagger API documentation](https://migreylopez.github.io/invoice/)

## Deployment

1. Build the shadowJar:
```
./gradlew clean build shadowJar
```
2. Start the service & database through docker-compose and create the DynamoDB table:
```
make start
```

To stop the service & database:
```
make stop
```