FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./demo/build/libs/*.jar
COPY ./demo/src/main/resources ./demo/src/main/resources
COPY ${JAR_FILE} ./demo/app.jar
WORKDIR /demo
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080

