package kevin.project.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        // Create a server socket that listens on port 1234
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started and listening on port 12345");
            while (true) {
                // Wait for a client to connect
                try (Socket socket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    out.println("Hello, client! I received your message.");
                    // Read a message sent by the client
                    String message = in.readLine();
                    System.out.println("Received: " + message);

                    // Send a message to the client
                }
            }

        }
    }
}
