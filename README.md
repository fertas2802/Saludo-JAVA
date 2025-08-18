# JAVA SALUDO SERVLET WEBAPP

## Overview
A simple Java web application that greets users and saves their names to a PostgreSQL database using servlets and Jetty.

## Features
- Greet users by name via a servlet endpoint.
- Save user names to a PostgreSQL table (`usuarios`).

## Technologies
- Java 11
- Servlets (javax.servlet)
- Jetty (embedded web server)
- Maven (build and dependency management)
- PostgreSQL (database)
- HTML/JavaScript frontend

## Project Structure
- `src/main/java/` — Java source code (servlets, main class)
- `src/main/webapp/` — Frontend files (`index.html`, `scripts.js`)
- `pom.xml` — Maven configuration
- `target/` — Compiled classes and build artifacts (auto-generated)

## Key Dependencies
- `org.eclipse.jetty:jetty-server` and `jetty-servlet`: Embedded server and servlet support
- `javax.servlet:javax.servlet-api`: Servlet API
- `org.postgresql:postgresql`: PostgreSQL JDBC driver
- `junit:junit`: For unit testing (optional)

## Database
- PostgreSQL
- Table: `usuarios` with column `nombre_usuario`
- Connection configured in `GuardarServlet.java`

## How to Build and Run
0. Setear variable de entorno DATABASE_URL
- Powershell (local): 
    $env:DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin"
- Bash (local):
    export DATABASE_URL="jdbc:postgresql://localhost:5432/postgres?user=postgres&password=admin"

- Environment Variable (Cloud)
    ##formato jdbc:postgresql://[HOST_RENDER]:5432/[NOMBRE_DB]?user=[USUARIO]&password=[CONTRASEÑA]&sslmode=require
              jdbc:postgresql://dpg-d2d74njuibrs739askp0-a.oregon-postgres.render.com:5432/postgres_y4t6?user=postgres_y4t6_user&password=clYNAKL6IEDBhLuLoAe6PkqGZxpysdDu&sslmode=require
1. Build: `mvn clean package`
2. Run: `mvn exec:java`
3. Visit [http://localhost:8080](http://localhost:8080)

## Author
Fernando
