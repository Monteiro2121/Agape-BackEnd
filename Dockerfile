# === Stage 1: Build the JAR ===
FROM maven:3.9.6-eclipse-temurin-17 AS buildcompiler
WORKDIR /app

# Copy the source code and build config files
COPY pom.xml .
COPY src ./src

# Compile and package the application (skipping tests for faster CI/CD build)
RUN mvn clean package -DskipTests

# === Stage 2: Run the JAR ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR directly from the compiler stage instead of your local machine
COPY --from=buildcompiler /app/target/agape-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]