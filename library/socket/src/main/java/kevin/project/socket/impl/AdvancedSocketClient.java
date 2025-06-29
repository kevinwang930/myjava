package kevin.project.socket.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0
 * @ClassName SocketClient
 * @Description TODO
 * @Date 2024/7/27
 **/
public class AdvancedSocketClient {

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test() throws InterruptedException {


        String hostName = "127.0.0.1";
        int portNumber = 8989;


        AtomicLong total = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable runnable = () -> {
            long cnt = 0;
            long c = System.currentTimeMillis();
            try (Socket socket = new Socket(hostName, portNumber); PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(), true); BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {
                while (System.currentTimeMillis() - c < 1000) {

                    writer.println("hello from client");
                    String inputLine;
                    inputLine = in.readLine();
                    //                System.out.println("Received: " + inputLine);
                    cnt++;
                }
                System.out.println("thread " + Thread.currentThread().getName() + " finished " + cnt + " requests in " + (System.currentTimeMillis() - c));
                total.getAndAdd(cnt);
            } catch (Exception e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("total: " + total);
    }

}
