# sring boot build image 
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=container:latest
# kubenetes apply container image 
kubectl apply -f deployment.yaml -f service.yaml

# kubenetes redeployment
kubectl rollout restart deployment <deployment-name>
