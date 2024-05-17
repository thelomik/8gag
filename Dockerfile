FROM maven:3.8.4-openjdk-11 as build
LABEL authors="BartekChojnacki"

WORKDIR /app
COPY pom.xml .
COPY src ./scr
COPY docker-compose.yml .
RUN mvn clean install


FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/8gag-0.0.1-SNAPSHOT.jar ./demo-aws.jar
EXPOSE 8080
EXPOSE 8888
CMD ["java","-jar","demo-aws.jar"]
