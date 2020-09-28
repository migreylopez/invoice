start: up create-table

up: stop
	docker-compose -f docker-compose.yaml up --force-recreate -d

stop:
	docker-compose down

shadowJar:
	./gradlew clean build shadowJar

create-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb create-table \
		--table-name Invoice \
		--attribute-definitions AttributeName=id,AttributeType=S \
		--key-schema AttributeName=id,KeyType=HASH \
		--provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
		--endpoint 'http://docker.for.mac.localhost:8000'

describe-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb describe-table --table-name Invoice --endpoint 'http://docker.for.mac.localhost:8000'
