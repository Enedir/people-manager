
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY web /tmp/web/
COPY domain /tmp/domain/
COPY persistence /tmp/persistence/
WORKDIR /tmp/
RUN mvn clean install -Pdocker

FROM openjdk:11-jdk-alpine
COPY --from=MAVEN_TOOL_CHAIN /tmp/web/target/sajadv-pm-server.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]