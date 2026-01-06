# Use lightweight Java 17 runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy Spring Boot jar
COPY target/*.jar app.jar

# Expose Eureka port
EXPOSE 8761

# Run Eureka Server
ENTRYPOINT ["java", "-jar", "app.jar"]
