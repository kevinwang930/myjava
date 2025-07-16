package kevin.project.socket.impl.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;
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

    private final EventLoopGroup group;

    private static final AtomicLong total = new AtomicLong(0);

    private final StatisticHandler statisticHandler = new StatisticHandler();

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.group = new NioEventLoopGroup(4);
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
                             p.addLast(new LineBasedFrameDecoder(1024), new StringDecoder(), new StringEncoder(),
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

    public void shutdown() throws InterruptedException {
        Thread.sleep(1000);
        group.shutdownGracefully(0L, 10L, TimeUnit.SECONDS);
        long cc = statisticHandler.counter.get();
        long ct = total.addAndGet(statisticHandler.counter.get());
        log.info("Client shutdown. received {}，total {}", cc , ct);
    }

    private static class StatisticHandler extends SimpleChannelInboundHandler<String> {
        public AtomicLong counter = new AtomicLong(0);


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            counter.getAndIncrement();
        }
    }

    public static void test() {
        NettyClient nettyClient = new NettyClient("192.168.1.107", 8989);
        try {
            nettyClient.start();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 1000) {
                nettyClient.sendMessage("Hello \n");
            }
        } catch (Exception e) {
            log.error("Failed to start client", e);
        } finally {
            try {

                nettyClient.shutdown();
            } catch (Exception e) {
                log.error("Failed to shutdown client", e);
            }
        }
    }


    @SneakyThrows
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.submit(NettyClient::test);
        executorService.shutdown();
        executorService.awaitTermination(15, TimeUnit.SECONDS);
                test();
        log.info("total: {}", total);
    }
}
