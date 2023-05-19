FROM openjdk:17-alpine AS build-stage

WORKDIR /app

COPY .. .
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests

FROM openjdk:17-alpine
# Set working directory to nginx asset directory
WORKDIR /app

COPY --from=build-stage /app/target .
COPY --from=build-stage /app/docker-entrypoint.sh .

RUN sed -i 's/\r$//g' docker-entrypoint.sh
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["java", "-jar", "application.jar"]

EXPOSE 8081