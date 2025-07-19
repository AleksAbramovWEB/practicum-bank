## Сдача проектной работы одиннадцатого спринта
#### Apache Kafka

### ✅  Устанавливаем переменное окружение в .env для локального запуска и тестов

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

```

### ✅  Устанавливаем переменное окружение в .env для jenkins

```
# путь до локального kubeconfig-файла (абсолютный!)
KUBECONFIG_PATH=
# токен для ghcr
GHCR_TOKEN=
# имя пользователя GitHub
GITHUB_USERNAME=
# токен для GitHub
GITHUB_TOKEN=
# репозиторий
GITHUB_REPOSITORY=
# Docker Registry
DOCKER_REGISTRY=
# пароль к базе данных
DB_PASSWORD=
```

### ✅ Добавить репозиторий с kafka
```bash
helm repo add bitnami "https://helm-charts.itboon.top/bitnami" --force-update
"bitnami" has been added to your repositories
helm search repo bitnami/kafka
NAME            CHART VERSION   APP VERSION     DESCRIPTION
bitnami/kafka   32.3.1          4.0.0         
```

### ✅ Добавить репозиторий с zipkin
```bash
helm repo add zipkin "https://openzipkin.github.io/zipkin-helm" --force-update
"zipkin" has been added to your repositories
```

### ✅ Пример ручной установки чартов в кластер
```bash
cd k8s/charts/
helm upgrade --install practicum-bank . \  
                                  -n dev --create-namespace \  
                                  -f values.yaml \  
                                  -f values-dev.yaml \  
                                  -f secret-values-dev.yaml  
```

### ✅ Включить NGINX Ingress в Minikube
```bash
minikube addons enable ingress  
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