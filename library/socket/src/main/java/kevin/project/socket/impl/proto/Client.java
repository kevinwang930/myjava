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

        long start = System.currentTimeMillis();
        long cnt = 0;
        while (System.currentTimeMillis() - start < 1000) {
            try (
                    Socket socket = new Socket(hostName, portNumber)
            ) {
//                System.out.printf("socket opened\n");
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                Simple.Person person = Simple.Person.newBuilder().setName("test").setAge(30).build();
                Simple.Request request = Simple.Request.newBuilder().setPerson(person).build();
                request.writeDelimitedTo(outputStream);
                Simple.Response response = Simple.Response.parseDelimitedFrom(inputStream);
                System.out.println("server response: "+ response.getGreeting());
                cnt++;
                System.out.println(cnt);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        System.out.println(cnt);

    }
}
