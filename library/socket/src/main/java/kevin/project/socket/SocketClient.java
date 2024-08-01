package kevin.project.socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @ClassName SocketClient
 * @Description TODO
 * @Date 2024/7/27
 **/
public class SocketClient {

    public static void main(String[] args) {
        test();
    }

    public static void test() {


        String hostName = "127.0.0.1";
        int portNumber = 8989;


        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger error = new AtomicInteger(0);
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                30,
                TimeUnit.SECONDS,
                new LimitedQueue<>(10));
        long c = System.currentTimeMillis();
        while (System.currentTimeMillis() - c < 1000) {
            executorService.submit(() -> {
                int index = total.incrementAndGet();
                System.out.println("thread" + Thread.currentThread().getName() + " index " + index);
                try (
                        Socket socket = new Socket(hostName, portNumber);
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()))
                ) {
                    System.out.println("thread" + Thread.currentThread().getName() + " index " + index + " port:" + socket.getLocalPort());
                    writer.println("hello from client");
                    String inputLine;
                    inputLine = in.readLine();
                    System.out.println("Received: " + inputLine);
                    System.out.println("socket closed " + index);
                } catch (Exception e) {
                    System.err.println("Don't know about host " + hostName);
                    System.exit(1);
                }
            });
        }
        executorService.shutdown();
        System.out.println("total: " + total.get());
        System.out.println("error: " + error.get());

    }
}