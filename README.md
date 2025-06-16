## Сдача проектной работы девятого спринта
#### Разработка приложений с использованием микросервисной архитектуры

### ✅  Устанавливаем переменное окружение в .env

```
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_PORT=
POSTGRES_HOST=

KC_HOSTNAME=
KC_PORT=
KC_HTTP_ENABLED=true
KC_PROXY=edge
KC_DB=
KC_DB_USERNAME=
KC_DB_PASSWORD=
KC_DB_URL=

KEYCLOAK_ADMIN=
KEYCLOAK_ADMIN_PASSWORD=
KEYCLOAK_URL=
OAUTH2_REALM=

ACCOUNT_SERVICE_POSTGRES_DB=

BASE_URL=http://localhost:8080
CONFIG_URL=http://localhost:8888

GIT_URL=
GIT_USERNAME=
GIT_TOKEN=

CONSUL_HOST=
CONSUL_PORT=

```

### ✅ Сборка/старт приложения
```bash
chmod +x run-local.sh
./run-local.sh
```

### ✅ Запуск тестов
```bash
chmod +x run-test.sh
./run-test.sh
```

### ✅ Создание образов и запуск контейнеров
```bash
chmod +x run-docker.sh
./run-docker.sh
```