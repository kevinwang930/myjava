package kevin.project.socket.impl.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import kevin.project.socket.impl.ServerContext;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface NettyMessageHandler {
    void onMessage(ChannelHandlerContext handlerContext, String message) throws IOException;
}
