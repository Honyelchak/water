package edu.cug.water.crawler.job.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class RequestParam {

    private String url;
    private int requestMethod;
    private Map<String, String> headers;
    private Map<String, String> params;
}
