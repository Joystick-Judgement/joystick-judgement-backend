FROM openjdk:21-jdk as first_stage

WORKDIR /app
COPY . /app
CMD gradlew build

FROM amazoncorretto:21-alpine as second_dockerstage

ARG PROFILE
ARG PORT

ENV PROFILE $PROFILE
ENV PORT $PORT
ENV TZ="America/Sao_Paulo"

RUN echo "PROFILE: $PROFILE"
RUN echo "PORT: $PORT"

WORKDIR /app
COPY --from=first_stage /app/build/libs/*.jar /app/ms-game.jar

ENTRYPOINT java \
    -Dspring.profiles.active=$PROFILE \
    -Duser.timezone=$TZ \
    -jar /app/ms-game.jar

EXPOSE $PORT



