# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the compiled Java application JAR file into the container
COPY target/weather-app-1.0-SNAPSHOT.jar /app/weather-app.jar

# Set the entrypoint for the container
ENTRYPOINT ["java", "-jar", "/app/weather-app.jar"]

# Expose the port if necessary (for web-based apps)
EXPOSE 8085
