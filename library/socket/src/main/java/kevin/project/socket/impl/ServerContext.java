package kevin.project.socket.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ServerContext {
    private final Set<SocketChannel> clients = Collections.synchronizedSet(new HashSet<>());

    public void addClient(SocketChannel client) {
        clients.add(client);
    }

    public void removeClient(SocketChannel client) {
        clients.remove(client);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(SocketChannel client, String message) throws IOException {
        if (client.isConnected()) {
            client.write(ByteBuffer.wrap(message.getBytes()));
        }
    }

    public void broadcastMessage(String message) throws IOException {
        synchronized (clients) {
            for (SocketChannel client : clients) {
                sendMessage(client, message);
            }
        }
    }
}
