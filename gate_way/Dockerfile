FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gate_way.jar
ENTRYPOINT ["java","-jar","/gate_way.jar"]
