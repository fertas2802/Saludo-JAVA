
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.DefaultServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Asociar el servlet con la ruta "/hola"
        handler.addServlet(new ServletHolder(new HelloServlet()), "/hola");

        // Configurar servlet para archivos estáticos
        ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("resourceBase", "src/main/webapp");
        defaultServlet.setInitParameter("dirAllowed", "true");
        handler.addServlet(defaultServlet, "/");

        server.setHandler(handler);
        
        System.out.println("Servidor iniciado en http://0.0.0.0:8080");
        server.start();
        server.join();
    }
}
