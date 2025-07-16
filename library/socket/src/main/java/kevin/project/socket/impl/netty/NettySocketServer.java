package kevin.project.socket.impl.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import kevin.project.socket.impl.EchoMessageHandler;
import kevin.project.socket.impl.MessageHandler;

import javax.sound.sampled.Line;


/**
 * @version 1.0
 * @ClassName NettySocketServer
 * @Description TODO
 * @Date 2024/8/1
 **/
public class NettySocketServer {

    private final int port;
    private final NettyServerContext context;
    private final NettyMessageHandler messageHandler;

    public NettySocketServer(int port) {
        this.port = port;
        this.context = new NettyServerContext();
        this.messageHandler = new NettyEchoMessageHandler();
    }

    public static void main(String[] args) throws Exception {
        int port = 8989;
        new NettySocketServer(port).start();
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;
        if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup(1);
            workerGroup = new EpollEventLoopGroup();
        } else {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
        }
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new LineBasedFrameDecoder(1024),
                                    new StringDecoder(),
                                    new StringEncoder(),
                                    new ServerHandler()
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, false);

            ChannelFuture f = b.bind(port).sync();
            System.out.println("Server started on port " + port);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static class ServerHandler extends SimpleChannelInboundHandler<String> {


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            String response = "received: " + msg + "\n";
            ctx.writeAndFlush(response);
        }
    }
}
