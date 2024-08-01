package kevin.project.socket.impl;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface MessageHandler {
    void onMessage(SocketChannel client, String message, ServerContext context) throws IOException;
}
