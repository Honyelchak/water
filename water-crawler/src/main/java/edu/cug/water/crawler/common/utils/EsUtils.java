package edu.cug.water.crawler.common.utils;


import edu.cug.water.crawler.common.constant.EsConstants.*;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class EsUtils {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    public boolean insert() throws IOException {

        RestClient lowLevelClient = restHighLevelClient.getLowLevelClient();

        Request request = new Request(RequestMethod.GET.getValue(), "/");

        Response response = lowLevelClient.performRequest(request);

        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] headers = response.getHeaders();
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
        return true;
    }

    public ResponseEntity getOne(String indexName, String typeName, String id) {
        Request request = new Request(RequestMethod.GET.getValue(), indexName + "/" + typeName);
        GetResponse documentFields = restHighLevelClient.get(request);
    }

    public GetResponse getList(@NotNull String indexName, @NotNull String typeName, String id) {

    }
}
