package kevin.project.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class HelloWorldClient {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
            System.out.println("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";

        long start = System.currentTimeMillis();
        int cnt = 0;
        while (System.currentTimeMillis() - start < 2000) {
            ManagedChannel channel = null;
            try {
                 channel = ManagedChannelBuilder.forTarget(target)
                        .usePlaintext()
                        .build();
                HelloWorldClient client = new HelloWorldClient(channel);
                client.greet("test");
                cnt++;
            } finally {
                if (channel != null) {
                    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                }
            }
        }
        System.out.println(cnt);
    }
}