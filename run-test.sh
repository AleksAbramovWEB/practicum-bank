#!/bin/bash

set -e

# shellcheck disable=SC2046
export $(grep -v '^#' .env | xargs)

./gradlew services:account:test
./gradlew services:blocker:test
./gradlew services:cash:test
./gradlew services:exchange:test
./gradlew services:exchange-generator:test
./gradlew services:front-ui:test
./gradlew services:notification:test
./gradlew services:transfer:test