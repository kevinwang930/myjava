package kevin.project.socket;

import java.io.*;
import java.net.Socket;


public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine = in.readLine();
        System.out.println(inputLine);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("goodbye from client");

        in.close();
        out.close();
        socket.close();
    }
}
