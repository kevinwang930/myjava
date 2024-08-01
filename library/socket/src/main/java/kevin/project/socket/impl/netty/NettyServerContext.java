package kevin.project.socket.impl.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @ClassName NettyServerContext
 * @Description TODO
 * @Date 2024/8/1
 **/
public class NettyServerContext {
    private final Set<Channel> clients = Collections.synchronizedSet(new HashSet<>());

    public void addClient(Channel client) {
        clients.add(client);
    }

    public void removeClient(Channel client) {
        clients.remove(client);

        client.close();

    }

    public void sendMessage(Channel client, String message) throws IOException {
        if (client.isOpen()) {
            client.write(ByteBuffer.wrap(message.getBytes()));
        }
    }

    public void broadcastMessage(String message) throws IOException {
        synchronized (clients) {
            for (Channel client : clients) {
                sendMessage(client, message);
            }
        }
    }
}
