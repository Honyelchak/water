package edu.cug.water.crawler.common.utils;


import edu.cug.water.crawler.common.constant.EsConstants.MapperConstants;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class EsUtils {

    @Autowired
    private BBossESStarter bBossESStarter;

    private final static String mapper =  MapperConstants.MAPPER_DIR.getValue() + "/" + MapperConstants.GATEWAY_MAPPER.getValue();

    /**
     * addDateDocument() 会将文档添加到"{indexName}-{CurrentDate}"库中。
     * @param indexName
     * @param indexType
     * @param bean
     * @return
     * @throws IOException
     */
    public String addDateDocument(String indexName, String indexType, Object bean) throws IOException {
        ClientInterface restClient = bBossESStarter.getConfigRestClient(mapper);
        return restClient.addDateDocument(indexName, indexType, bean);
    }

    public String addDocument(String indexName, String indexType, Object bean) {
        ClientInterface restClient = bBossESStarter.getConfigRestClient(mapper);
        return restClient.addDocument(indexName, indexType, bean);
    }

    public String addDocuments(String indexName, String indexType, List<?>list) {
        ClientInterface restClient = bBossESStarter.getConfigRestClient(mapper);
        return restClient.addDocuments(indexName, indexType, list);
    }

//    public String addMapObject(String indexName, String indexType, List<Map> list) {
//
//    }

    public ESDatas<?> searchList(String index, String dslName, Map param, Class<?> cls) {
        ClientInterface restClient = bBossESStarter.getConfigRestClient(mapper);
        return restClient.searchList(index + "/search", dslName, param, cls);
    }
}
