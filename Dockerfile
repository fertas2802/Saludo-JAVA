# Etapa de construcción
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copia solo los archivos necesarios primero
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
# Otorga permisos de ejecución
RUN chmod +x mvnw
# Descarga dependencias (cachea este paso)
RUN ./mvnw dependency:go-offline
# Copia el resto del código
COPY src/ src/
# Compila la aplicación
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]