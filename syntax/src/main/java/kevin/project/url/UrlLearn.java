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

    public void urlConcatLearn() {

    }

    public void paramLearn() throws MalformedURLException {
        URL url = new URL("baidu.com?a=b");
        System.out.println(url.getQuery());

    }

    public void contentTypeLearn() throws IOException, URISyntaxException {
        System.out.println(URLConnection.getFileNameMap().getContentTypeFor("file.mp4"));
        System.out.println(URLConnection.getFileNameMap().getContentTypeFor("sample_20.mp4"));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        UrlLearn urlLearn = new UrlLearn();
//        urlLearn.urlConnectionLearn();
        urlLearn.decoderLearn();
        urlLearn.urlPathLearn();
        urlLearn.contentTypeLearn();
        urlLearn.paramLearn();
    }
}
