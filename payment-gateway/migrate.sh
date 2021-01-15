#!/usr/bin/env bash

# Database URL in the following format:
# driver://user:password@host:port/dbname?query
# For more details on PostgreSQL URLs see:
# https://github.com/golang-migrate/migrate/tree/master/database/postgres
DB_URL='postgres://fp:local@localhost:5556/payment_gateway?sslmode=disable'

# Migrations directory path relative to this script,
# without the "./" charachters at the begining.
MIGRATIONS_RELATIVE_PATH='deployment/database/migration'

# Tag of the migrate image to use.
MIGRATE_TAG='v4.10.0'

# Get the absoute path to the migrations directory.
SCRIPT_ABSOLUTE_PATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
MIGRATIONS_ABSOLUTE_PATH="$SCRIPT_ABSOLUTE_PATH/$MIGRATIONS_RELATIVE_PATH"

docker run \
  -v "$MIGRATIONS_ABSOLUTE_PATH":/migrations \
  --network host --rm -it \
  migrate/migrate:"$MIGRATE_TAG" \
  -path /migrations \
  -database "$DB_URL" \
  "$@"
