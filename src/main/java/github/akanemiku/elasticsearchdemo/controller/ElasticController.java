package github.akanemiku.elasticsearchdemo.controller;

import github.akanemiku.elasticsearchdemo.utils.ResultVO;
import github.akanemiku.elasticsearchdemo.utils.ResultVOUtil;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * ES 6.3.2 CRUD操作
 * 见，https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/java-rest-high-supported-apis.html
 */
@RestController
@RequestMapping("/es")
public class ElasticController {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    private String index = "posts";
    private String type = "doc";
    private String id = "1";


    /**
     * 新增文档
     *
     * @param json
     * @return
     * @throws IOException
     */
    @PutMapping("/put")
    public ResultVO putIndex(@RequestBody String json) throws IOException {
        IndexRequest request = new IndexRequest(index, type, id);

        System.out.println(json);

        request.source(json, XContentType.JSON);
        IndexResponse response = client.index(request);
        return ResultVOUtil.success(response.toString());
    }

    /**
     * 查询文档
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/get")
    public ResultVO getIndex() throws IOException {
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse response = client.get(getRequest);
        return ResultVOUtil.success(response.getSource());
    }

    /**
     * 删除文档
     *
     * @return
     * @throws IOException
     */
    @DeleteMapping("/delete")
    public ResultVO deleteIndex() throws IOException {
        DeleteRequest request = new DeleteRequest(index);
        DeleteResponse response = client.delete(request);
        return ResultVOUtil.success(response.toString());
    }

    /**
     * 修改文档
     *
     * @param json
     * @return
     * @throws IOException
     */
    @PostMapping("/post")
    public ResultVO postIndex(@RequestBody String json) throws IOException {
        UpdateRequest request = new UpdateRequest(index, type, id);

        System.out.println(json);

        request.doc(json, XContentType.JSON);
        UpdateResponse response = client.update(request);
        return ResultVOUtil.success(response.toString());
    }

}
