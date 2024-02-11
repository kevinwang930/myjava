package kevin.project;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.io.IOException;

public class JettyServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(new QueuedThreadPool(20));

        // Configure SSL context factory for HTTPS.
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();

        // Set the key store path
        sslContextFactory.setKeyStorePath("/path/to/keystore.jks");
        sslContextFactory.setKeyStorePassword("keystorePassword");

        // Set the trust store path
        sslContextFactory.setTrustStorePath("/path/to/truststore.jks");
        sslContextFactory.setTrustStorePassword("truststorePassword");

        // Require client certificate authentication.
//        sslContextFactory.setNeedClientAuth(true);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        connector.setIdleTimeout(3000);
        server.addConnector(connector);
        server.setHandler(new AbstractHandler() {

            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println("<h1>Hello, World!</h1>");
            }
        });

        server.start();
        server.join();
    }
}