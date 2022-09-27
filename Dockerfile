FROM openjdk:17
LABEL maintainer="rubendepaz1"
RUN mkdir /app
COPY /target/holaluz-test-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]

