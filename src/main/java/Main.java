import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        // Usar el puerto de Render o 8080 en local
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Server server = new Server(port);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Servlets
        handler.addServlet(new ServletHolder(new HelloServlet()), "/hola");
        handler.addServlet(new ServletHolder(new GuardarServlet()), "/guardar");

        // Archivos estáticos
        ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("resourceBase", "src/main/webapp");
        defaultServlet.setInitParameter("dirAllowed", "true");
        handler.addServlet(defaultServlet, "/");

        server.setHandler(handler);

        System.out.println("Servidor iniciado en http://0.0.0.0:" + port);
        server.start();
        server.join();
    }
}
