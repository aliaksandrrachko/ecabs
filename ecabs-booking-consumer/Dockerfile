FROM openjdk:8-jdk-alpine
ARG CONT_IMG_VER
ENV CONT_IMG_VER=v1.0.0
RUN echo $CONT_IMG_VER
VOLUME /tmp
EXPOSE 8080 8080
ARG JAR_FILE=ecabs-booking-consumer-main/target/ecabs-booking-consumer-main-0.0.1-SNAPSHOT.jar
WORKDIR /opt/ecabs
COPY ${JAR_FILE} ecabs-booking-consumer.jar
ENTRYPOINT ["java", "-jar", "ecabs-booking-consumer.jar"]
HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET http://localhost:8080/actuator/health \
                | jq --exit-status '.status == "UP"' || exit 1