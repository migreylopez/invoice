start: up create-table

up:
	docker-compose -f docker-compose.yaml up -d

create-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb create-table \
		--table-name Invoice \
		--attribute-definitions AttributeName=id,AttributeType=S \
		--key-schema AttributeName=id,KeyType=HASH \
		--provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
		--endpoint 'http://host.docker.internal:8000'

describe-table:
	docker run --rm -v $(PWD)/.aws:/root/.aws amazon/aws-cli:2.0.6 dynamodb describe-table --table-name Invoice --endpoint 'http://host.docker.internal:8000'
