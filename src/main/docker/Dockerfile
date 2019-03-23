FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD springboot-demo.jar app.jar

EXPOSE 8080
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar /app.jar