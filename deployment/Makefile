docker-start:
	docker-compose up -d

docker-stop:
	-docker-compose down 2>/dev/null

db-start: docker-start create-table

db-restart: docker-stop docker-start create-table

create-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb create-table \
		--table-name Invoice \
		--attribute-definitions AttributeName=id,AttributeType=S \
		--key-schema AttributeName=id,KeyType=HASH \
		--provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
		--endpoint 'http://docker.for.mac.localhost:8000'

describe-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb describe-table --table-name Invoice --endpoint 'http://docker.for.mac.localhost:8000'
