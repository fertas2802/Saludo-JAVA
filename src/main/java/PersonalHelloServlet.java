import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PersonalHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String nombre = req.getParameter("nombre");
        if (nombre == null || nombre.isEmpty()) {
            nombre = "invitado";
        }

        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h1>Hola! " + nombre + "</h1>");
        resp.getWriter().write("<p><a href='/'>Volver al inicio</a></p>");
        resp.getWriter().write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        resp.getWriter().write("Método POST no soportado. Use GET.");
    }
}
