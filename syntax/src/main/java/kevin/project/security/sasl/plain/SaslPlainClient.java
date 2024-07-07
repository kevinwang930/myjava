package kevin.project.security.sasl.plain;

import javax.security.auth.callback.*;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * @version 1.0
 * @ClassName SaslPlainClient
 * @Description TODO
 * @Date 7/6/24
 **/
public class SaslPlainClient {
    public static void main(String[] args) throws SaslException {
        String[] mechanisms = {"PLAIN"};
        SaslClient saslClient = Sasl.createSaslClient(mechanisms, null, null, "example", new HashMap<>(), new ClientCallbackHandler());

        try (Socket socket = new Socket("localhost", 9090);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = "\0admin\0password"; // PLAIN payload format: [authzid] UTF8NUL authcid UTF8NUL passwd
            out.write(message.getBytes());
            byte[] response = saslClient.evaluateChallenge(new byte[0]);
            System.out.println(new String(response));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Server says: " + line);
                if (line.contains("successful")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientCallbackHandler implements CallbackHandler {
        @Override
        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {

                if (callbacks[i] instanceof NameCallback) {

                    NameCallback ncb = (NameCallback) callbacks[i];

                    ncb.setName("admin");

                } else if (callbacks[i] instanceof PasswordCallback) {

                    PasswordCallback pcb = (PasswordCallback) callbacks[i];

                    pcb.setPassword("password".toCharArray());

                } else if (callbacks[i] instanceof RealmCallback) {

                    RealmCallback rcb = (RealmCallback) callbacks[i];

                    rcb.setText("java.com");

                } else {

                    throw new UnsupportedCallbackException(callbacks[i]);

                }

            }
        }
    }
}
