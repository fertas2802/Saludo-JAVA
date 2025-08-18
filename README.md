# Saludo Servlet Java

## Overview
Aplicación web sencilla desarrollada en Java utilizando Servlets y Jetty como servidor embebido. Permite mostrar un saludo y guardar nombres en una base de datos PostgreSQL. Funciona tanto **localmente** como en **Render** mediante Docker.

---

## Features

- `/hola` — endpoint **GET** que devuelve un saludo en HTML.
- `/guardar` — endpoint **POST** que recibe un parámetro `nombre` y lo inserta en la tabla `personas` de PostgreSQL.
- Compatible con ejecución local y cloud (Render).
- Logging profesional mediante **SLF4J**.
- Manejo de errores y validación de parámetros.

---

## Tecnologías

- Java 17
- Jetty 11 (Servidor embebido)
- Servlets (Jakarta EE)
- PostgreSQL
- Maven (Gestión de dependencias y build)
- SLF4J (Logging)
- Docker (Despliegue en cloud)

---

## Estructura del Proyecto

.
├── src/
│ └── main/
│ ├── java/
│ │ ├── Main.java
│ │ ├── HelloServlet.java
│ │ └── GuardarServlet.java
│ └── resources/
├── target/
│ └── HolaMundoServlet-1.0-SNAPSHOT.jar
├── pom.xml
└── Dockerfile


- `Main.java` — Punto de entrada, configura Jetty y los servlets.  
- `HelloServlet.java` — Endpoint `/hola`.  
- `GuardarServlet.java` — Endpoint `/guardar` con conexión a PostgreSQL y compatibilidad local/cloud.  
- `Dockerfile` — Permite build y deploy en Render usando Docker.  
- `pom.xml` — Dependencias y plugins Maven.  

---

## Acceso a Base de Datos

- Se utiliza **PostgreSQL**.  
- La conexión se define mediante la variable de entorno `DATABASE_URL`.  
- Local:  
    jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin&ssl=false
- Cloud (Render u otro host):  
    jdbc:postgresql://<HOST>:<PORT>/<DB>?user=<USER>&password=<PASSWORD>
    `PORT` debe configurarse tambien en 8080 para Cloud.

- Tabla requerida:
```sql
CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  nombre_usuario TEXT NOT NULL
);

Dependencias Principales (pom.xml)
org.eclipse.jetty:jetty-server:11.0.20
org.eclipse.jetty:jetty-servlet:11.0.20
jakarta.servlet:jakarta.servlet-api:6.0.0
org.postgresql:postgresql:42.7.3
org.junit.jupiter:junit-jupiter:5.10.1 (test)
org.eclipse.jetty:jetty-util, jetty-util-ajax, jetty-slf4j-impl

Compilar y Correr Local:
    Definir variable de entorno para la DB local:
    Windows (PowerShell):
        $env:DATABASE_URL="jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin&ssl=false"
    Linux / macOS (Bash):
        export DATABASE_URL="jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin&ssl=false"

    Compilar con Maven local (Bash):
        mvn clean package
    Ejecutar la app (Bash):
        java -jar target/HolaMundoServlet-1.0-SNAPSHOT.jar
    Probar endpoints:
        GET /hola
        curl http://localhost:8080/hola
        POST /guardar
        curl -X POST -d "nombre=Fernando" http://localhost:8080/guardar

Deploy en Cloud (Render):
Crear un Web Service en Render usando Docker.
Conectar el repositorio público de GitHub.
Configurar variable de entorno DATABASE_URL para la base de datos cloud.
Configurar variable de entorno PORT = 8080
Render detectará el Dockerfile y construirá la imagen:
    # Etapa de build
    FROM maven:3.9.2-eclipse-temurin-17 AS build
    WORKDIR /app
    COPY pom.xml .
    RUN mvn dependency:go-offline
    COPY src/ src/
    RUN mvn clean package

    # Etapa de ejecución
    FROM eclipse-temurin:17-jre
    WORKDIR /app
    COPY --from=build /app/target/HolaMundoServlet-1.0-SNAPSHOT.jar app.jar
    EXPOSE 8080
    CMD ["java", "-jar", "app.jar"]
Al finalizar el deploy, acceder a la URL de Render:
/hola → saludo
/guardar → POST con nombre

Autor Fernando