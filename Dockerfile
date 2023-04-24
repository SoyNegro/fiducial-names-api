FROM maven:3-openjdk-11 AS build
COPY . /home/maven/src
WORKDIR /home/maven/src
RUN mvn package

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/maven/src/target/Names-1.0.jar /app/namesapi.jar
ENTRYPOINT ["java","-jar","/app/namesapi.jar"]