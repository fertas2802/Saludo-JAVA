import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuardarServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GuardarServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        if (nombre == null || nombre.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Parámetro 'nombre' obligatorio");
            return;
        }

        String jdbcUrl = System.getenv("DATABASE_URL");

        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Variable de entorno DATABASE_URL no definida");
            logger.error("DATABASE_URL no definida");
            return;
        }

        // Ajuste SSL: solo agregar sslmode=require si no es localhost y no está definido
        if (!jdbcUrl.contains("sslmode=") && !jdbcUrl.contains("localhost")) {
            if (!jdbcUrl.contains("?")) {
                jdbcUrl += "?sslmode=require";
            } else {
                jdbcUrl += "&sslmode=require";
            }
        }

        try (Connection conn = DriverManager.getConnection(jdbcUrl)) {
            String sql = "INSERT INTO usuarios (nombre_usuario) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.executeUpdate();
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Nombre guardado correctamente: " + nombre);
            logger.info("Nombre guardado: {}", nombre);
        } catch (Exception e) {
            logger.error("Error al guardar en la BD", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al guardar en la BD: " + e.getMessage());
        }
    }

    // Opcional: manejar GET para que devuelva un mensaje simple
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        resp.getWriter().write("Método GET no soportado en /guardar. Use POST.");
    }
}
