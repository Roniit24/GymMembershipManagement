# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose the port your app runs on (Render will set $PORT)
EXPOSE 9009

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
