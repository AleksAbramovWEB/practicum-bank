# рестартонуть под
kubectl rollout restart deployment account -n dev
# посмотреть список подов в нейспесе дев
kubectl get pods -n dev
# посмотреть список по всему
kubectl get all -n dev
# удалить чат постгресс
helm uninstall postgres -n dev
# Удаляет все PVC (PersistentVolumeClaim) в namespace dev.
kubectl delete pvc -n dev --all
# сборка
./gradlew :services:front-ui:bootJar
docker build --no-cache -t front-ui-practicum-bank:latest -f services/front-ui/Dockerfile .
minikube image load front-ui-practicum-bank:latest
kubectl rollout restart deployment cash -n dev
# установить чарт
helm install front-ui k8s/charts/front-ui \
  -n dev --create-namespace \
  -f k8s/charts/front-ui/values.yaml \
  -f k8s/charts/front-ui/secret-values-dev.yaml
# Обновить чарт
helm upgrade exchange-generator k8s/charts/exchange-generator \
  -n dev \
  -f k8s/charts/exchange-generator/values.yaml \
  -f k8s/charts/exchange-generator/secret-values-dev.yaml
# Подключиться к базе данных
kubectl exec -it -n dev postgres-0 -- psql -U bank
# Просмотр логов
kubectl logs -n dev deploy/account
kubectl logs -n dev deploy/account -f
kubectl logs -n dev -l app=keycloak -f
# Посмотреть секреты
kubectl get secret postgres-secret -n dev -o yaml
# Посмотреть конфиг
kubectl get configmap account-config -n dev -o yaml
# Удалить образ
minikube ssh -- docker image rm blocker-practicum-bank:latest --force
# Загрузить образ
minikube image load blocker-practicum-bank:latest
# Удалить все поды
kubectl delete pods --all -n dev
# Удалить все в нейспейсе
kubectl delete all --all -n dev
# Удалить нейспейс
kubectl delete namespace dev
# Удалить ресурс
kubectl delete pv keycloak-theme-pv
# Прокинуть порт наружу
kubectl port-forward svc/keycloak 8080:8080 -n dev
# Включить NGINX Ingress в Minikube
kubectl minikube addons enable ingress
# Применить namespace
kubectl apply -f k8s/namespaces/dev.yaml
# Подтянуть саб чарты в umbrella
cd k8s/umbrella
helm dependency update
# или
helm dependency build
# установить umbrella
 helm upgrade --install practicum-bank . \
                                      -n dev --create-namespace \
                                      -f values-dev.yaml \
                                      -f secret-values-dev.yaml
 # проброска тунеля 19901508Oi!
 minikube tunnel
 # остановить
 minikube stop
 # запустить
 minikube start