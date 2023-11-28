#!/bin/bash

#Make sure you're using JDK 21 in your bash and evaluate this script in root directory of ms-game folder
#If you receive access denied when you try to execute this script, then try use chmod +x start-dockerized-app-as-native.sh before use it

docker-compose -f docker-compose.yaml up database -d
./gradlew bootBuildImage --imageName=ms-game-native-image
docker-compose -f docker-compose-native.yaml up ms-game-native -d