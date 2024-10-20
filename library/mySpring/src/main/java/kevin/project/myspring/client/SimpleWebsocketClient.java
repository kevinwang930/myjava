package kevin.project.myspring.client;

import jakarta.websocket.*;
import org.springframework.web.socket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class SimpleWebsocketClient {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server");
        try {
            session.getBasicRemote().sendText("Hello from client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Message from server: " + message);
    }

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("ws://localhost:8510/ws");
            container.connectToServer(SimpleWebsocketClient.class, uri);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }
}
