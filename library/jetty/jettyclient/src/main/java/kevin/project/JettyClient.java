package kevin.project;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;

public class JettyClient {

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();
        Request request = httpClient.newRequest("http://localhost:8080");
        ContentResponse response = request.send();
        System.out.println(response.getContentAsString());
        httpClient.stop();
    }
}