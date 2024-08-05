# Use the official Maven image to create a build artifact.
# The build stage
FROM maven:3.6.3-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests
# List contents of target directory and output to build log
RUN echo "Contents of /home/app/target:" && ls -l /home/app/target

# The run stage
FROM openjdk:11-jre-slim
# Copy the jar file with a wildcard pattern
COPY --from=build /home/app/target/*.jar /usr/local/lib/
# List contents of /usr/local/lib and output to build log
RUN echo "Contents of /usr/local/lib:" && ls -l /usr/local/lib
EXPOSE 8082
# Use wildcard pattern in ENTRYPOINT
ENTRYPOINT ["sh", "-c", "java -jar /usr/local/lib/*.jar"]