package kevin.project.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @ClassName ElasticSearchLearn
 * @Description Update: Add JacksonJsonpMapper and Elasticsearch integration
 * @Date 9/1/24
 **/
public class ElasticSearchLearn {

    private static String INDEX_NAME = "posts";


    public static void elasticSearchLearn() {
        RestClient restClient = getRestClient();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        try {

            addIndices(client);

            listAll(client);
            search(client);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the client when done
            try {
                if (restClient != null) {
                    restClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addIndices(ElasticsearchClient client) throws IOException {

        try {

            DeleteIndexResponse deleteResponse = client.indices()
                                                       .delete(d -> d.index(INDEX_NAME));
            if (deleteResponse.acknowledged()) {
                System.out.println("Index " + INDEX_NAME + " has been deleted successfully.");
            } else {
                System.out.println("Failed to delete index " + INDEX_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.indices()
              .create(c -> c.index(INDEX_NAME)
                            .settings(s -> s.analysis(a -> a.analyzer("ik_smart_analyzer",
                                                                    aa -> aa.custom(ca -> ca.tokenizer("ik_smart")))
                                                            .analyzer("ik_max_word_analyzer",
                                                                    aa -> aa.custom(ca -> ca.tokenizer("ik_max_word")))
                                                            .analyzer("combined_chinese_analyzer", aa -> aa.custom(
                                                                    ca -> ca.tokenizer("ik_smart")
                                                                            .filter("lowercase", "ngram")))))
                            .mappings(m -> m.properties("message", p -> p.text(
                                    t -> t.analyzer("combined_chinese_analyzer")
                                          .searchAnalyzer("ik_smart_analyzer")))))


        ;

        //                         Index a document
        addIndex(client, "test", "优衣库更衣室");
        addIndex(client, "test", "优衣库更衣室换衣服");
        addIndex(client, "test", "优衣库更衣室试穿");
        addIndex(client, "test", "中华人民共和国");
        addIndex(client, "test", "trying do a lot of gaming");
        client.indices()
              .refresh(r -> r.index(INDEX_NAME));
    }

    private static void addIndex(ElasticsearchClient client, String user, String message) throws IOException {
        Map<String, Object> document = new HashMap<>();
        document.put("user", user);
        document.put("message", message);

        IndexResponse indexResponse = client.index(i -> i.index(INDEX_NAME)
                                                         .document(JsonData.of(document)));
        System.out.println("Insert response: " + message + indexResponse.result());
    }

    private static void search(ElasticsearchClient client) throws IOException {
        SearchResponse<Map> searchResponse;
        String query = "trying";
        searchResponse = client.search(s -> s.index(INDEX_NAME)
                                             .query(q -> q.match(m -> m.field("message")
                                                                       .query(query))), Map.class);
        print(searchResponse, "match query", query);

        String query1 = "北京优衣库试穿";
        searchResponse = client.search(s -> s.index(INDEX_NAME)
                                             .query(q -> q.match(m -> m.field("message")
                                                                       .query(query1))), Map.class);
        print(searchResponse, "Chinese match query", query1);

        searchResponse = client.search(s -> s.index(INDEX_NAME)
                                             .query(q -> q.match(m -> m.field("message")
                                                                       .query("中华"))), Map.class);
        print(searchResponse, "Chinese match query", "中华");


        // Search for documents
        searchResponse = client.search(s -> s.index(INDEX_NAME)
                                             .query(q -> q.fuzzy(f -> f.field("message")
                                                                       .value("trying out Elasticsearch")
                                                                       .fuzziness("AUTO")
                                                                       .transpositions(true))), Map.class);

        print(searchResponse, "fuzzy query", query);
    }

    private static void print(SearchResponse<Map> searchResponse, String queryMethod, String query) {
        System.out.println(queryMethod + " " + query + " Total hits: " + searchResponse.hits()
                                                                                       .total()
                                                                                       .value());

        for (var hit : searchResponse.hits()
                                     .hits()) {
            System.out.println("Found document: " + hit.source());
        }
    }

    private static void listAll(ElasticsearchClient client) throws IOException {
        SearchResponse<Map> searchResponse = client.search(s -> s.index(INDEX_NAME)
                                                                 .query(q -> q.matchAll(
                                                                         new MatchAllQuery.Builder().build()))
                                                                 .size(1000),
                // Adjust size as needed
                Map.class);

        List<Hit<Map>> hits = searchResponse.hits()
                                            .hits();
        System.out.println("Total hits: " + searchResponse.hits()
                                                          .total()
                                                          .value());

        for (Hit<Map> hit : hits) {
            System.out.println("Document ID: " + hit.id());
            System.out.println("Document content: " + hit.source());
            System.out.println("---");
        }
    }

    private static RestClient getRestClient() {
        RestClient restClient = null;
        try {
            // Trust all certificates for test
            SSLContext sslContext = SSLContextBuilder.create()
                                                     .loadTrustMaterial((chain, authType) -> true)
                                                     .build();

            // Create credentials provider
            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("root", "123456"));

            restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                                   //                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                                   //                            .setSSLContext(sslContext)
                                   //                            .setSSLStrategy(new SSLIOSessionStrategy(sslContext, NoopHostnameVerifier.INSTANCE))
                                   //                            .setDefaultCredentialsProvider(credentialsProvider))
                                   .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        return restClient;
    }

    public static void main(String[] args) {
        elasticSearchLearn();
    }
}
