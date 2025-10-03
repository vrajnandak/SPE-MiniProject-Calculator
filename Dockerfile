# Use official Java image
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy Maven-built JAR into container
COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

