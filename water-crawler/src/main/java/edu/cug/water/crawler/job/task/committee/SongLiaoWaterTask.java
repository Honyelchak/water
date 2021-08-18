package edu.cug.water.crawler.job.task.committee;

import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.SongLiaoWater;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("job:SongLiaoWater")
public class SongLiaoWaterTask implements ITask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String REGEX =  "<font style=\"font-size:12px\">(.*)</font>";
    private static final String REGEX_FLOW = "<SCRIPT>cvt\\('(.*)'\\);</SCRIPT>";
    static final Pattern pattern = Pattern.compile(REGEX);
    static final Pattern patternFlow = Pattern.compile(REGEX_FLOW);

    private static final String url = "http://www.slwr.gov.cn/swjgzfw/sssq.asp";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "www.slwr.gov.cn");
        headers.set("Connection", "keep-alive");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        headers.set("Referer", "http://www.slwr.gov.cn/");
        headers.set("Accept-Encoding", "gzip, deflate");
        headers.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=gbk");

        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("GBK"));
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        //ResponseEntity<String> response = requestUtils.request(url, HttpMethod.GET, entity);

        logger.info("SongLiaoWater Task status code: {}", response.getStatusCode());

        List<SongLiaoWater> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("SongLiaoWater response.getBody() is null or \"\" ");
        else
            parse = parse(response.getBody());

        String save = save(parse);
        logger.info(save);

    }

    private String save(List<SongLiaoWater> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.SONG_LIAO_WATER.getValue(),
                EsConstants.TypeConstant.SONG_LIAO_WATER.getValue(), list);
    }

    private List<SongLiaoWater> parse(String body) {
//        logger.info(body);

        List<SongLiaoWater> results = new ArrayList<>();

        Matcher matcher = pattern.matcher(body);

        List<String> match = new ArrayList<>();
        while (matcher.find()) {
            match.add((matcher.group(1) + "").trim());
        }
        int cnt = match.size(), base = 5;
        for (int i = 1; i < cnt / 5; i ++)
        {
            SongLiaoWater o = new SongLiaoWater();
            o.setStationName(match.get(i * base)).
                    setTime(match.get(i * base + 1)).
                    setWaterStage(dealWaterStage(match.get(i * base + 2))).
                    setFlow(match.get(i * base + 3)).
                    setFluctuation(dealFluctuation(match.get(i * base + 4)));
            o.setCreateTime(DateUtils.formatDateTime(new Date()));
            results.add(o);
        }
        logger.info("SongLiaoWater converts results：{}", results.size());
        return results;
    }

    private String dealFluctuation(String s) {
        if(s.indexOf("down") != -1) return "down";
        else if (s.indexOf("equal") != -1) return "equal";
        else if (s.indexOf("up") != -1) return "up";
        return "";
    }

    private String dealWaterStage(String s){
        Matcher matcher = patternFlow.matcher(s);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return s;
    }
}
