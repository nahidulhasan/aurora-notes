FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY build/libs/aurora-notes-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
