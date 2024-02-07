FROM openjdk:17-jdk-alpine
COPY target/scala-2.13/akka-http-quickstart-assembly-0.1.0-SNAPSHOT.jar /app.jar

EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]