package kevin.project.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


import java.net.URL;


public class Client {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:12345");

        for (int i = 0; i < 5; i++) {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "keep-alive");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Cleanup
            in.close();
            conn.disconnect();

            System.out.println("Response Content: " + content.toString());
        }
    }
}
