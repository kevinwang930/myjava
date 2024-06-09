package kevin.project.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

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

    public void decoderLearn() throws IOException {
        String decode = URLDecoder.decode("视频%20", "UTF-8");
        System.out.println(decode);
        System.out.println(decode.length());
    }

    public void urlPathLearn() throws IOException, URISyntaxException {
        URL url = new URL("https://example.com/path/resource?query=123");
        URI uri = new URI(url.getProtocol(),  "kevin.net", url.getPath(), url.getQuery(),null);
        System.out.println(uri.toURL());

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        UrlLearn urlLearn = new UrlLearn();
//        urlLearn.urlConnectionLearn();
        urlLearn.decoderLearn();
        urlLearn.urlPathLearn();

    }
}
