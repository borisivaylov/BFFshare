# Use the official OpenJDK image as the base image
#FROM openjdk:17

# Set the working directory within the container
#WORKDIR /bff

# Copy the packaged JAR file from the target directory into the container
#COPY rest/target/BFFShareApplication.jar bff.jar
# Expose the port that your application runs on
#EXPOSE 8083

# Define the command to run your application
#CMD ["java", "-jar", "bff.jar"]


# Use the official OpenJDK image as the base image
FROM openjdk:17

# Set the working directory within the container
WORKDIR /bff

# Copy the packaged JAR file from the target directory into the container
COPY ./rest/target/BFFShareApplication.jar bff.jar
# Expose the port that your application runs on
EXPOSE 8083

# Define the command to run your application
CMD ["java", "-jar", "bff.jar"]



