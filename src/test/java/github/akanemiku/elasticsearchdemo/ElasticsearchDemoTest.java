package github.akanemiku.elasticsearchdemo;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchDemoTest {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        IndexRequest request = new IndexRequest("posts", "doc", "1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse response = client.index(request);
        System.out.println(response.toString());
    }

    @Test
    public void testGetIndex() throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "doc",
                "1");
        GetResponse getResponse = client.get(getRequest);
        System.out.println(getResponse.toString());
    }

}
