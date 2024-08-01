package kevin.project.socket.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @ClassName SocketServer
 * @Description TODO
 * @Date 2024/7/27
 **/
public class Nio2SocketServer {
    private static final ConcurrentHashMap<String, AsynchronousSocketChannel> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost", 8989));

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Void attachment) {
                server.accept(null, this); // Accept next connection
                try {
                    String clientAddress = client.getRemoteAddress().toString();
                    clients.put(clientAddress, client);
                    System.out.println("New client connected: " + clientAddress);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    readFromClient(client, buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

        // Keep the main thread alive
        Thread.currentThread().join();
    }

    private static void readFromClient(AsynchronousSocketChannel client, ByteBuffer buffer) {
        client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (result == -1) {
                    try {
                        disconnectClient(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                attachment.flip();
                String message = new String(attachment.array(), 0, result);
                System.out.println("Received: " + message);

                // Echo back
                client.write(ByteBuffer.wrap(("Echo: " + message).getBytes()));

                attachment.clear();
                readFromClient(client, attachment);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                try {
                    disconnectClient(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void disconnectClient(AsynchronousSocketChannel client) throws IOException {
        String clientAddress = client.getRemoteAddress().toString();
        clients.remove(clientAddress);
        System.out.println("Client disconnected: " + clientAddress);
        client.close();
    }
}


