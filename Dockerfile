FROM openjdk:21-jdk

# Set the maintainer (optional)
LABEL maintainer="Sthembiso Vinjwa <vinjwacr7@gmail.com>"

# Define a build argument to specify the JAR file location
ARG JAR_FILE=target/job-application-tracker-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} /app.jar

# Specify the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
