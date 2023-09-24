package kevin.learn.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8443;
        String keystoreFile = "keystore.jks";
        String keystorePassword = "password";

        // Set up the SSL context and load the keystore
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(keystoreFile), keystorePassword.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, keystorePassword.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        // Create a server socket factory
        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();

        // Create a server socket and start listening
        try (SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(port)) {
            System.out.println("SSL server started on port " + port);
            while (true) {
                try (SSLSocket socket = (SSLSocket) serverSocket.accept()) {
                    System.out.println("Client connected");

                    // Read and print the client's message
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = reader.readLine();
                    System.out.println("Received: " + line);

                    // Send a response to the client
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println("Hello, client!");
                }
            }
        }
    }
}