FROM maven:3.8.4-openjdk-11 as build
LABEL authors="BartekChojnacki"

WORKDIR /app
COPY src ./src
COPY ./*.json .
COPY ./*.yml .
COPY ./*.xml .
COPY ./*.sh .

RUN mvn clean install


FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/8gag-0.0.1-SNAPSHOT.jar ./demo-aws.jar
EXPOSE 8080
EXPOSE 5432
EXPOSE 8888
CMD ["java","-Dfile.encoding=UTF-8","-jar","demo-aws.jar"]
