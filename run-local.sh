#!/bin/bash

set -e

# shellcheck disable=SC2046
export $(grep -v '^#' .env | xargs)

SERVICES=(
  "services:account:bootJar;services/account/build/libs"
  "services:blocker:bootJar;services/blocker/build/libs"
  "services:cash:bootJar;services/cash/build/libs"
  "services:exchange-generator:bootJar;services/exchange-generator/build/libs"
  "services:exchange:bootJar;services/exchange/build/libs"
  "services:front-ui:bootJar;services/front-ui/build/libs"
  "services:notification:bootJar;services/notification/build/libs"
  "services:transfer:bootJar;services/transfer/build/libs"
)

for SERVICE_ENTRY in "${SERVICES[@]}"; do
  IFS=";" read -r TASK JAR_PATH <<< "$SERVICE_ENTRY"

  echo "➡️ Сборка: ./gradlew $TASK"
  ./gradlew "$TASK"

  JAR_FILE=$(find "$JAR_PATH" -maxdepth 1 -name "*.jar" ! -name "*original*" ! -name "*stubs*" | head -n 1)

  if [[ -z "$JAR_FILE" ]]; then
    echo "❌ JAR-файл не найден в $JAR_PATH"
    exit 1
  fi

  echo "✅ Найден JAR: $JAR_FILE. Запуск..."
  java -jar "$JAR_FILE" &
  PIDS+=($!)
done

trap "echo '🛑 Остановка всех процессов...'; kill ${PIDS[*]}" SIGINT SIGTERM

wait "${PIDS[@]}"
