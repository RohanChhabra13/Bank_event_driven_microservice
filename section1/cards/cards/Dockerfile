#base image containing Java runtime
FROM openjdk:21-jdk-slim

#owner or maintainer
LABEL "org.opencontainers.image.authors"="https://www.linkedin.com/in/rohanchhabra-"

#Add the application jar to image
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]