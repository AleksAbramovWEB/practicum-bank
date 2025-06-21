./gradlew :services:account:bootJar
docker build --no-cache -t account-practicum-bank:latest -f services/account/Dockerfile .
minikube image load account-practicum-bank:latest
kubectl rollout restart deployment account -n dev
