start-db: docker-kill docker-start create-table

docker-kill:
	-docker kill dynamodb-local 2>/dev/null

docker-start:
	docker-compose up -d

create-table:
	aws dynamodb create-table \
        --table-name Invoice \
        --attribute-definitions AttributeName=id,AttributeType=S \
        --key-schema AttributeName=id,KeyType=HASH \
        --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
        --endpoint 'http://localhost:8000'

describe-table:
	aws dynamodb describe-table --table-name Invoice --endpoint 'http://localhost:8000'
