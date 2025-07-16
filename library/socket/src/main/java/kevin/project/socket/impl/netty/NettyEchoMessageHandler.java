package kevin.project.socket.impl.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import kevin.project.socket.impl.MessageHandler;
import kevin.project.socket.impl.ServerContext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @version 1.0
 * @ClassName EchoMessageHandler
 * @Description TODO
 * @Date 2024/7/29
 **/
public class NettyEchoMessageHandler implements NettyMessageHandler {
    @Override
    public void onMessage(ChannelHandlerContext ctx, String message) throws IOException {
        String response = "received: " + message + "\n";
        ctx.writeAndFlush(response);

    }
}
