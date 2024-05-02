FROM maven:3.8-openjdk-17 as build

EXPOSE 8082

COPY ./target/demo-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
