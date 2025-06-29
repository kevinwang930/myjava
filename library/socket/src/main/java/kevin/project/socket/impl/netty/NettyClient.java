package kevin.project.socket.impl.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class NettyClient {
    private final String host;

    private final int port;

    private Channel channel;

    private final EventLoopGroup group = new NioEventLoopGroup(2);

    private final StatisticHandler statisticHandler = new StatisticHandler();

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) {
                             ChannelPipeline p = ch.pipeline();
                             p.addLast(
                                     new LineBasedFrameDecoder(1024),
                                     new StringDecoder(),
                                     new StringEncoder(),
                                     statisticHandler);

                         }
                     });

            ChannelFuture future = bootstrap.connect(host, port)
                                            .sync();
            channel = future.channel();
        } catch (Exception e) {
            log.error("Failed to start client", e);
            group.shutdownGracefully();
            throw e;
        }
    }

    public void sendMessage(String message) {
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(message);
        }
    }

    public void shutdown() {
        group.shutdownGracefully(0L,10L, TimeUnit.SECONDS);
        log.info("Client shutdown. received {} messages", statisticHandler.counter);
    }

    private static class StatisticHandler extends SimpleChannelInboundHandler<String> {
        public long counter = 0L;

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
//            log.info("Received message: {}", msg);
            counter++;
        }
    }

    public static void test()  {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8989);
        try {
            nettyClient.start();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 2000) {
                nettyClient.sendMessage("Hello World \n");
            }
        } catch (Exception e) {
            log.error("Failed to start client", e);
        } finally {
            nettyClient.shutdown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        test();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
