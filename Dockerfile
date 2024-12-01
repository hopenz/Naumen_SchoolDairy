FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/Naumen_SchoolDairy-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "Naumen_SchoolDairy-1.0-SNAPSHOT.jar"]