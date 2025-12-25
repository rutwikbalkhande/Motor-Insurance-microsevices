# 1️⃣ Use lightweight Java 17 runtime
FROM eclipse-temurin:17-jre-alpine

# 2️⃣ Set working directory inside container
WORKDIR /app

# 3️⃣ Copy Spring Boot JAR file
COPY target/Claim_Module-0.0.1-SNAPSHOT.jar app.jar

# 4️⃣ Expose application port
EXPOSE 8084

# 5️⃣ Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]