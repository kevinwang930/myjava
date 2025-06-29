package kevin.project.socket.impl.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import kevin.project.socket.impl.netty.NettyServerContext;
import kevin.project.socket.impl.proto.Simple;

import java.io.IOException;

/**
 * @version 1.0
 * @ClassName EchoMessageHandler
 * @Description TODO
 * @Date 2024/7/29
 **/
public class NettyProtobufMessageHandler implements ProtobufMessageHandler {
    @Override
    public void onMessage(ChannelHandlerContext ctx, Simple.Request message, NettyServerContext context) throws IOException {
        String response;

        if (message.hasAnimal()) {
            response = "received Animal: " + message.getAnimal().getName() + "\n";
        } else if (message.hasPerson()) {
            response = "received Person: " + message.getPerson().getName() + "\n";
        } else {
            response = "received unknown message";
        }
        Simple.Response responseP = Simple.Response.newBuilder().setGreeting(response).build();
        ctx.writeAndFlush(responseP);

    }
}
