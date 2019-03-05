FROM maven:3.6.0-jdk-8-alpine
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
EXPOSE 8080
#RUN mvn spring-boot:run
CMD ["mvn", "spring-boot:run", "-Dspring.profiles.active=local"]
