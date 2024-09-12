# Stage 1: Build the application
FROM ubuntu:22.04 AS build

# Install OpenJDK 17 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src /app/src

# Package the application
RUN mvn clean install -DskipTests

# Stage 2: Run the application using a minimal OpenJDK 17 runtime
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar /app/Library-Management-System.jar

EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "/app/Library-Management-System.jar"]
