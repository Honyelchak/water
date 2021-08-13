package edu.cug.water.crawler.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${elastic.search.host-name}")
    private String hostName;

    @Value("${elastic.search.port}")
    private int port;


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostName, port, "http")
                )
        );
    }

}
