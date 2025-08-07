import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Asociar el servlet con la ruta "/hola"
        handler.addServlet(new ServletHolder(new HelloServlet()), "/hola");

        // Directorio con archivos estáticos como index.html
        handler.setResourceBase("src/main/webapp");
        handler.setWelcomeFiles(new String[]{"index.html"});

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
