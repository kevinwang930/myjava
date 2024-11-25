#!/bin/bash

# Variables
IMAGE_NAME="kevinwang930/docker"
VERSION="1.0.1"
cd ..
# Build Java application
/opt/homebrew/Cellar/openjdk/23.0.1/libexec/openjdk.jdk/Contents/Home/bin/java -Dmaven.multiModuleProjectDirectory=/Users/marlse/code/myjava -Djansi.passthrough=true -Dmaven.home=/opt/homebrew/Cellar/maven/3.9.9/libexec -Dclassworlds.conf=/opt/homebrew/Cellar/maven/3.9.9/libexec/bin/m2.conf   -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /opt/homebrew/Cellar/maven/3.9.9/libexec/boot/plexus-classworlds.license:/opt/homebrew/Cellar/maven/3.9.9/libexec/boot/plexus-classworlds-2.8.0.jar org.codehaus.classworlds.Launcher  -s /Users/marlse/.m2/settings-2nd.xml -Dmaven.repo.local=/Users/marlse/.m2/repository-2nd package

# Build Docker image
docker build -t ${IMAGE_NAME}:${VERSION} .
docker tag ${IMAGE_NAME}:${VERSION} ${IMAGE_NAME}:latest

# Push to Docker Hub (optional)
docker push ${IMAGE_NAME}:${VERSION}
docker push ${IMAGE_NAME}:latest