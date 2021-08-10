FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service_discovery.jar
ENTRYPOINT ["java","-jar","/service_discovery.jar"]
