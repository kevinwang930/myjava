package kevin.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.io.IOException;

public class JettyServletServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        // Configure SSL context factory for HTTPS.
//        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();

        // Allow all client connections and trust everything
//        sslContextFactory.setTrustAll(true);
//        sslContextFactory.setEndpointIdentificationAlgorithm(null); // Disable endpoint identification
//        sslContextFactory.setIncludeProtocols("TLSv1.2", "TLSv1.3");
        // Require client certificate authentication.
        //        sslContextFactory.setNeedClientAuth(true);

//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(9500);
//        connector.setIdleTimeout(3000);
//        server.addConnector(connector);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(HomeServlet.class, "/");
        servletContextHandler.addServlet(AboutServlet.class, "/about");
        server.setHandler(servletContextHandler);
        try {

            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
    public static class HomeServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            System.out.println("request for homepage");
            resp.setContentType("text/html");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter()
                .println("<html><body><h1>Hello, World!</h1></body></html>");
        }
    }


    public static class AboutServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("request for about page");
            resp.setContentType("text/html");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter()
                .println("<html><body><h1>About Page</h1><p>This is the About page of the server.</p></body></html>");
        }
    }
}



