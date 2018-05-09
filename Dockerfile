FROM openjdk:8-slim as builder
WORKDIR .

ENV APP_HOME=/root/dev/app/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

COPY pom.xml mvnw $APP_HOME
COPY .mvn $APP_HOME/.mvn

# download dependencies
RUN ./mvnw clean package -DskipTests -Dskip.boot.repackage=true
COPY . .
RUN ./mvnw package





FROM openjdk:8-slim as app
WORKDIR /root/
COPY --from=builder /root/dev/app/target/demo-app*.jar .
RUN mv demo-app*.jar demo-app.jar
EXPOSE 8080

HEALTHCHECK --start-period=5s --timeout=3s --interval=15s \
    CMD curl http://localhost:8080/actuator/health || exit 1

CMD ["java","-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar", "demo-app.jar"]
