package kevin.project.socket.impl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version 1.0
 * @ClassName SocketServer
 * @Description TODO
 * @Date 2024/7/27
 **/
public class SimpleSocketServer {
    public static void main(String[] args) throws IOException {
        int port = 8989;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String inputLine;
                inputLine = in.readLine();
                System.out.println("Received: " + inputLine);
                System.out.println("finish read");
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("Hello from the server!");
                System.out.println("finish write");
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
