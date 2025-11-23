# Java 21 base image
FROM amazoncorretto:21

# Workdir inside container
WORKDIR /app

# Copy built Spring Boot jar into the image
# adjust the jar name if yours is different
COPY target/io-cpu-local-benchmark-1.0.0.jar app.jar

# Document the port your app listens on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
