# Use Zulu OpenJDK as the base image
FROM azul/zulu-openjdk:22.0.2-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copy the source code
COPY src ./src

# Install Maven in the container
RUN apt-get update && apt-get install -y maven=3.9.8

# Package the application
RUN ./mvnw clean package

# Copy the packaged jar file to the working directory
COPY target/one-care-connect-api.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
