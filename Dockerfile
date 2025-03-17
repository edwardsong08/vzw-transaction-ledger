# Use an official lightweight Java runtime as a base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the build output into the container
COPY build/libs/vzw-transaction-ledger-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app listens on (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
