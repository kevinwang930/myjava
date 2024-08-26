package kevin.project.socket.impl;

import kevin.project.socket.LimitedQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class SimpleSocketClient {

    public static void main(String[] args) {
        test();
    }

    public static void test() {


        String hostName = "127.0.0.1";
        int portNumber = 8989;


        long cnt = 0;
        long c = System.currentTimeMillis();
        while (System.currentTimeMillis() - c < 1000) {
            try (
                    Socket socket = new Socket(hostName, portNumber);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))
            ) {
                writer.println("hello from client");
                String inputLine;
                inputLine = in.readLine();
                System.out.println("Received: " + inputLine);
                cnt++;
            } catch (Exception e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);
            }
        }

        System.out.println("total: " + cnt);
    }

}
