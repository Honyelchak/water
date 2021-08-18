package edu.cug.water.crawler.job.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ResponseParam {
    private String url;
    private int status;
    private Map<String, String> headers;
    
}
