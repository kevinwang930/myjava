package kevin.project.socket.impl;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName SocketServer
 * @Description TODO
 * @Date 2024/7/27
 **/
@Setter
@Getter
public class SelectorSocketServer {
    Selector clientSelector = null;



    public static void main(String[] args) throws IOException {
        SelectorSocketServer server = new SelectorSocketServer();
        server.start();
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        selector.wakeup();
        System.out.println("New client connected: " + client.getRemoteAddress());
    }

    private static void answerWithHandler(SelectionKey key, MessageHandler handler, ServerContext context) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = client.read(buffer);
        if (bytesRead == -1) {

            System.out.println("Client disconnected: " + client.getRemoteAddress());
            key.cancel();
            client.close();
            context.removeClient(client);
            return;
        }
        if (bytesRead > 0) {
            buffer.flip();
            String received = new String(buffer.array(), 0, buffer.limit()).trim();
            System.out.println("Received from " + client.getRemoteAddress() + ": " + received);
            handler.onMessage(client, received, context);
            buffer.clear();
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
        }
    }

    void start() throws IOException {
        clientSelector = Selector.open();
        Thread serverThread = new Thread(new ServerTask());
        serverThread.start();
        ExecutorService executorService = new ThreadPoolExecutor(20,50,10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        ServerContext context = new ServerContext();
        MessageHandler handler = new EchoMessageHandler();
        while (true) {
//            System.out.println("cient selector size " + clientSelector.keys().size());
            clientSelector.select(1000);

            Set<SelectionKey> selectedKeys = clientSelector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isReadable()) {
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    executorService.submit(() -> {
                        try {
                            answerWithHandler(key,handler,context);
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    });
//                    answerWithHandler(key,handler,context);
                }
                iter.remove();
            }
        }

    }

    class ServerTask implements Runnable {

        @Override
        public void run() {

            try {
                Selector selector = Selector.open();
                ServerSocketChannel serverSocket = ServerSocketChannel.open();
                serverSocket.bind(new InetSocketAddress("localhost", 8989));
                serverSocket.configureBlocking(false);
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    int select = selector.select();
                    System.out.println("server select count: " + select);

                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = selectedKeys.iterator();
                        while (iter.hasNext()) {
                            SelectionKey key = iter.next();
                            iter.remove();
                            if (key.isAcceptable()) {
                                register(clientSelector, serverSocket);
                            }
                        }

                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}


