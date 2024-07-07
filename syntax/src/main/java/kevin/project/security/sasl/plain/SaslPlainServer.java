package kevin.project.security.sasl.plain;

import javax.security.auth.callback.*;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @version 1.0
 * @ClassName SaslPlainServer
 * @Description TODO
 * @Date 7/6/24
 **/
public class SaslPlainServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server started");

        try (Socket clientSocket = serverSocket.accept()) {
            System.out.println("Received connection from " + clientSocket.getInetAddress());

            SaslServer saslServer = Sasl.createSaslServer("PLAIN", null, "example", new HashMap<>(), new CallbackHandler() {
                @Override
                public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                    for (Callback callback : callbacks) {
                        if (callback instanceof NameCallback) {
                            ((NameCallback) callback).setName("admin");
                        } else if (callback instanceof PasswordCallback) {
                            ((PasswordCallback) callback).setPassword("password".toCharArray());
                        } else {
                            throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
                        }
                    }
                }
            });

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                byte[] response = saslServer.evaluateResponse(message.getBytes());
                if (saslServer.isComplete()) {
                    out.println("OK Authentication successful");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
