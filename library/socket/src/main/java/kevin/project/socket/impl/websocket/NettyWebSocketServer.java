package kevin.project.socket.impl.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @version 1.0
 * @ClassName NettyWebSocketServer
 * @Description TODO
 * @Date 2024/8/1
 **/
public class NettyWebSocketServer {
    private final int port;

    public NettyWebSocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/websocket", null, true));
                            pipeline.addLast(new WebSocketFrameHandler());
                        }
                    });

            Channel ch = b.bind(port).sync().channel();
            System.out.println("WebSocket server started at port " + port);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
            if (msg instanceof TextWebSocketFrame) {
                String request = ((TextWebSocketFrame) msg).text();
                System.out.println("Received: " + request);
                ctx.channel().writeAndFlush(new TextWebSocketFrame("Message received: " + request));
            } else {
                String message = "Unsupported frame type: " + msg.getClass().getName();
                throw new UnsupportedOperationException(message);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new NettyWebSocketServer(port).run();
    }
}



