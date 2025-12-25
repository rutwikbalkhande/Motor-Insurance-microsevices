# Use lightweight Java 17 runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy Spring Boot jar
COPY target/*.jar app.jar

# Expose Gateway port
EXPOSE 8085

# Run API Gateway
ENTRYPOINT ["java", "-jar", "app.jar"]
