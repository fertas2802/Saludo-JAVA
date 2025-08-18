FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Especifica la clase main directamente (sin paquete)
ENTRYPOINT ["java", "-cp", "app.jar", "Main"] 