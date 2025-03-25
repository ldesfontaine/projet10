# Phase 1 : Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Pproduction

# Phase 2 : Runtime (production)
FROM openjdk:23-jdk-slim
WORKDIR /app
# Copie uniquement le jar construit
COPY --from=builder /app/target/spring-skeleton-1.0-SNAPSHOT.jar ./spring-skeleton-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "ton-application.jar"]
