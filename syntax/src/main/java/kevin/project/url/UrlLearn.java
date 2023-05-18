package kevin.project.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlLearn {
    public void urlConnectionLearn() throws IOException {

        URL url = new URL("http://www.baidu.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
//         DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//         out.writeBytes(content);
//         out.flush();
//         out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        connection.disconnect();
    }

    public static void main(String[] args) throws IOException {
        UrlLearn urlLearn = new UrlLearn();
        urlLearn.urlConnectionLearn();
    }
}
