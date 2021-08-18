package edu.cug.water.crawler.job.request;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestUtils {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> request(String url, HttpMethod requestMethod, HttpEntity httpEntity) {
        return restTemplate.exchange(url, requestMethod, httpEntity, String.class);
    }

}
