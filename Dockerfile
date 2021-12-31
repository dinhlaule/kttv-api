FROM maven:3.8.1-jdk-8
ARG JAR_FILE=target/kttv-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} kttv-api.jar
ENTRYPOINT ["java","-jar","/kttv-api.jar"]