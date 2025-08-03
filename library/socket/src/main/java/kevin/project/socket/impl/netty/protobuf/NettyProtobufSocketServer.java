package kevin.project.socket.impl.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import kevin.project.socket.impl.netty.NettyServerContext;
import kevin.project.socket.impl.proto.Simple;


/**
 * @version 1.0
 * @ClassName NettySocketServer
 * @Description TODO
 * @Date 2024/8/1
 **/
public class NettyProtobufSocketServer {

    private final int port;

    private final NettyServerContext context;

    private final ProtobufMessageHandler messageHandler;

    public NettyProtobufSocketServer(int port) {
        this.port = port;
        this.context = new NettyServerContext();
        this.messageHandler = new NettyProtobufMessageHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = 8989;
        new NettyProtobufSocketServer(port).start();
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;

        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline()
                       .addLast(

                               new ProtobufVarint32FrameDecoder(),
                               new ProtobufDecoder(Simple.Request.getDefaultInstance()),
                               new ProtobufVarint32LengthFieldPrepender(), new ProtobufEncoder(),
                               new ServerHandler(context, messageHandler));
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, false);

            ChannelFuture f = b.bind(port)
                               .sync();
            System.out.println("Server started on port " + port);
            f.channel()
             .closeFuture()
             .sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private class ServerHandler extends SimpleChannelInboundHandler<Simple.Request> {
        private final NettyServerContext context;

        private final ProtobufMessageHandler messageHandler;

        public ServerHandler(NettyServerContext context, ProtobufMessageHandler messageHandler) {
            this.context = context;
            this.messageHandler = messageHandler;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            //            System.out.println("New client connected: " + ctx.channel().remoteAddress());
            context.addClient(ctx.channel());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            System.out.println("Client disconnected: " + ctx.channel()
                                                            .remoteAddress());
            context.removeClient(ctx.channel());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Simple.Request msg) throws Exception {
            System.out.println("Received from " + ctx.channel()
                                                     .remoteAddress() + ": " + msg);
            messageHandler.onMessage(ctx, msg, context);
        }
    }
}
