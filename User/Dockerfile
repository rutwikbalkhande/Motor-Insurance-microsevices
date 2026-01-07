
# 1️⃣ base image Use lightweight Alpine JRE
FROM eclipse-temurin:17-jre-alpine

# -----------------------------
# 2️⃣ Set working directory
WORKDIR /app

# -----------------------------
# 3️⃣ Copy the jar from target folder (use the correct jar name)
COPY target/user-service.jar app.jar

# -----------------------------
# 4️⃣ Expose application port
# -----------------------------
EXPOSE 8081

# -----------------------------
# 5️⃣ Run your Spring Boot app
# -----------------------------
ENTRYPOINT ["java", "-jar", "app.jar"]
