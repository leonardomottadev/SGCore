# ===== Build =====
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Runtime =====
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=build /app/target/sgcore-cloud-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]