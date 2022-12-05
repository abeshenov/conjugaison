FROM openjdk:17-jdk-alpine
COPY build/libs/conjugaison-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
