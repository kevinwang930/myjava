package kevin.project.socket.impl.proto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version 1.0
 * @ClassName Server
 * @Description TODO
 * @Date 2024/8/9
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8989);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();


            Simple.Request request = Simple.Request.parseDelimitedFrom(input);
            Simple.Person person = request.getPerson();

            Simple.Response response = Simple.Response.newBuilder()
                    .setGreeting("Hello, " + person.getName())
                    .build();

            response.writeDelimitedTo(output);
            clientSocket.close();
        }
    }
}
