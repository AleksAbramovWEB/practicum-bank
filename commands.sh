# рестартонуть под
kubectl rollout restart deployment account -n dev
# посмотреть список подов в нейспесе дев
kubectl get pods -n dev
# удалить чат постгресс
helm uninstall postgres -n dev
# Удаляет все PVC (PersistentVolumeClaim) в namespace dev.
kubectl delete pvc -n dev --all
# сборка
./gradlew :services:cash:bootJar
docker build --no-cache -t cash-practicum-bank:latest -f services/cash/Dockerfile .
minikube image load cash-practicum-bank:latest
kubectl rollout restart deployment cash -n dev
# установить чарт
helm install cash k8s/charts/cash \
  -n dev --create-namespace \
  -f k8s/charts/cash/values.yaml \
  -f k8s/charts/cash/secret-values-dev.yaml
# Обновить чарт
helm upgrade account k8s/charts/account \
  -n dev \
  -f k8s/charts/account/values.yaml \
  -f k8s/charts/account/secret-values-dev.yaml
# Подключиться к базе данных
kubectl exec -it -n dev postgres-0 -- psql -U bank
# Просмотр логов
kubectl logs -n dev deploy/account
kubectl logs -n dev deploy/account -f
# Посмотреть секреты
kubectl get secret postgres-secret -n dev -o yaml
# Посмотреть конфиг
kubectl get configmap account-config -n dev -o yaml
# Удалить образ
minikube ssh -- docker image rm blocker-practicum-bank:latest --force
# Загрузить образ
minikube image load account-practicum-bank:latest
# Удалить все поды
kubectl delete pods --all -n dev