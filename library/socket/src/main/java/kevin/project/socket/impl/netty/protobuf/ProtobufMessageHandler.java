package kevin.project.socket.impl.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import kevin.project.socket.impl.netty.NettyServerContext;
import kevin.project.socket.impl.proto.Simple;

import java.io.IOException;

public interface ProtobufMessageHandler {
    void onMessage(ChannelHandlerContext handlerContext, Simple.Request message, NettyServerContext context) throws IOException;
}
