# Étape 1 : construire l'application
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : créer l'image exécutable
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN apk add --no-cache bash \
    && chmod +x /wait-for-it.sh
EXPOSE 8081
ENTRYPOINT ["/wait-for-it.sh", "mysql-goat-tutorats:3306", "--timeout=60", "--strict", "--", "java", "-jar", "app.jar"]
