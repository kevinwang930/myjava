package kevin.project.socket.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @version 1.0
 * @ClassName EchoMessageHandler
 * @Description TODO
 * @Date 2024/7/29
 **/
public class EchoMessageHandler implements MessageHandler {
    @Override
    public void onMessage(SocketChannel client, String message, ServerContext context) throws IOException {
        String response = "received: " + message + "\n";
        client.write(ByteBuffer.wrap(response.getBytes()));

    }
}
