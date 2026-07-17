# ---- Build stage: compile the application and produce the jar ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Cache dependencies separately from source so code changes don't re-download them
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# ---- Runtime stage: copy only the built jar into a slim JRE image ----
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

RUN addgroup -S app && adduser -S app -G app
COPY --from=build /build/target/frontend.jar app.jar
USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
