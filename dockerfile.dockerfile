FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/init-0.0.1-SNAPSHOT.jar /app/init-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/init-0.0.1-SNAPSHOT.jar"]
