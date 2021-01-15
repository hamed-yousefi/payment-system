
dm-migrate:
	docker-compose up -d db
	sh payment-gateway/migrate.sh up

maven-build:
	sh build/build.sh

docker-build:
	docker-compose build -f docker-compose.yaml

docker-up:
	docker-compose  -f docker-compose.yaml up -d