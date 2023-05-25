FROM openjdk:17
COPY target/procat-0.0.1-SNAPSHOT.jar /opt/procat.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/procat.jar"]