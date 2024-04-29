
# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information about who maintained the image
MAINTAINER hemanthkumar

# Add the application's jar to the image
COPY target/McProj01Sc02-Accounts-0.0.1-SNAPSHOT.jar McProj01Sc02-Accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "McProj01Sc02-Accounts-0.0.1-SNAPSHOT.jar"]