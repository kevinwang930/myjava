package kevin.project.security.sasl.plain;


import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.protocol.HttpCoreContext;

import java.io.IOException;
import java.util.Base64;

/**
 * @version 1.0
 * @ClassName PlainServer
 * @Description TODO
 * @Date 7/6/24
 **/
public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = ServerBootstrap.bootstrap()
                .setListenerPort(8080)
                .register("*", new BasicAuthHandler())
                .create();

        server.start();
        System.out.println("HTTP server started on port 8080");
    }

    static class BasicAuthHandler implements HttpRequestHandler {
        @Override
        public void handle(final ClassicHttpRequest request, final ClassicHttpResponse response, final HttpContext context) throws HttpException, IOException {
            HttpCoreContext coreContext = HttpCoreContext.adapt(context);
            EndpointDetails endpoint = coreContext.getEndpointDetails();
            Header authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.getValue().startsWith("Basic ")) {
                // Decode the basic authentication header
                String authToken = authHeader.getValue().substring(6);
                byte[] decodedBytes = Base64.getDecoder().decode(authToken);
                String decodedString = new String(decodedBytes);
                String[] credentials = decodedString.split(":");

                if ("admin".equals(credentials[0]) && "password".equals(credentials[1])) {
                    response.setCode(HttpStatus.SC_OK);
                    response.setEntity(new StringEntity("Authenticated successfully", ContentType.TEXT_PLAIN));
                } else {
                    response.setCode(HttpStatus.SC_UNAUTHORIZED);
                    response.setEntity(new StringEntity("Invalid credentials", ContentType.TEXT_PLAIN));
                }
            } else {
                response.setCode(HttpStatus.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Basic realm=\"User Visible Realm\"");
                response.setEntity(new StringEntity("Authentication Required", ContentType.TEXT_PLAIN));
            }
        }
    }

}
