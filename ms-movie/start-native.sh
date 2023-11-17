#Make sure you're using JDK 21 in your bash and evaluate this script in root directory of ms-movie folder
#If you receive access denied when you try to execute this script, then try use chmod +x start-native.sh before use it
./gradlew bootBuildImage
docker-compose up -d