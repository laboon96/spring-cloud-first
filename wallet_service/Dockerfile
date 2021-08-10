FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wallet_service.jar
ENTRYPOINT ["java","-jar","/wallet_service.jar"]