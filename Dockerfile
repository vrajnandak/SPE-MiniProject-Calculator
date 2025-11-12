# Use official Java image
FROM maven:3.8.5-eclipse-temurin-17 AS build

# Set working directory in container
WORKDIR /app

# Copy the pom.xml and download dependencies first (better for Docker caching)
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Package the application (skip tests for faster CI builds)
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

