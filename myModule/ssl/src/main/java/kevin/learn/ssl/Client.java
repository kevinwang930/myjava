package kevin.learn.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8443;
        String truststoreFile = "keystore.jks";
        String truststorePassword = "password";

        // Set up the SSL context and load the truststore
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(truststoreFile), truststorePassword.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        // Create a client socket factory
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        // Connect to the SSL server
        try (SSLSocket socket = (SSLSocket) ssf.createSocket(host, port)) {
            System.out.println("Connected to SSL server");

            // Send a message to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello, server!");

            // Read and print the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            System.out.println("Received: " + line);
        }
    }
}
