package kevin.project.socket.impl.proto;

import kevin.project.socket.LimitedQueue;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @ClassName SocketClient
 * @Description TODO
 * @Date 2024/7/27
 **/
public class Client {

    public static void main(String[] args) {
        test();
    }

    public static void test() {


        String hostName = "127.0.0.1";
        int portNumber = 8989;


        try (
                Socket socket = new Socket(hostName, portNumber)
        ) {
            System.out.printf("socket opened\n");
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            Simple.Person person = Simple.Person.newBuilder().setName("test").setAge(30).build();
            Simple.Request request = Simple.Request.newBuilder().setPerson(person).build();
            request.writeDelimitedTo(outputStream);
            Simple.Response response = Simple.Response.parseDelimitedFrom(inputStream);
            System.out.println("server response: "+ response.getGreeting());
        } catch (Exception e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }
    }
}
