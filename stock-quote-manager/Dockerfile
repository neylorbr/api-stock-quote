
FROM openjdk:8
ARG PROFILE
ARG ADITIONAL_OPTS
ENV PROFILE=${PROFILE}
WORKDIR /workspace

COPY /target/stock-quote-manager-0.0.1-SNAPSHOT.jar stock-quote-manager.jar

SHELL ["/bin/sh", "-c"]

EXPOSE '8081'
EXPOSE '5005'

CMD java -jar stock-quote-manager.jar --spring.profiles.active=${PROFILE}