#Make sure you're using JDK 21 in your bash and evaluate this script in root directory of ms-movie folder
#If you receive access denied when you try to execute this script, then try use chmod +x start-dockerized-app-as-native.sh before use it
./gradlew bootBuildImage --imageName=ms-movie-native-image
docker-compose up database ms-movie-native -d