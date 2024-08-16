# Stage 1: Build the application
FROM azul/zulu-openjdk:22.0.2-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Install Maven
RUN apt-get update && apt-get install -y maven=3.9.8

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM azul/zulu-openjdk:22.0.2-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/one-care-connect-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
