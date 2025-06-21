# рестартонуть под
kubectl rollout restart deployment account -n dev
# посмотреть список подов в нейспесе дев
kubectl get pods -n dev
# удалить чат постгресс
helm uninstall postgres -n dev
# Удаляет все PVC (PersistentVolumeClaim) в namespace dev.
kubectl delete pvc -n dev --all
# установить чарт
helm install postgres k8s/charts/postgres \
  -n dev --create-namespace \
  -f k8s/charts/postgres/values.yaml \
  -f k8s/charts/postgres/secret-values-dev.yaml
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
minikube ssh -- docker image rm account-practicum-bank:latest --force
# Загрузить образ
minikube image load account-practicum-bank:latest