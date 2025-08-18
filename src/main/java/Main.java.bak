import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

public class Main {
    public static void main(String[] args) throws Exception {
        // Render define el puerto en PORT; en local usa 8080 por defecto
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Server server = new Server(port);

        // Contexto y servlets
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Servlets de la app
        handler.addServlet(new ServletHolder(new HelloServlet()), "/hola");
        handler.addServlet(new ServletHolder(new GuardarServlet()), "/guardar");

        // Servir estáticos desde el classpath: src/main/resources/static (empaquetado en el JAR)
        handler.setBaseResource(Resource.newClassPathResource("/static"));
        handler.setWelcomeFiles(new String[]{"index.html"});

        // DefaultServlet para archivos estáticos y la raíz "/"
        ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("dirAllowed", "false");
        handler.addServlet(defaultServlet, "/");

        server.setHandler(handler);

        System.out.println("Servidor iniciado en http://0.0.0.0:" + port);
        server.start();
        server.join();
    }
}
