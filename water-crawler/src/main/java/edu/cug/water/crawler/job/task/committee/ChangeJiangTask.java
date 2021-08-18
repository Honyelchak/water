package edu.cug.water.crawler.job.task.committee;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.ChangeJiang;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("job:ChangeJiang")
public class ChangeJiangTask implements ITask {

    private static final String REGEX = "sssq = (.*);.*var sqHtml";
    static final Pattern pattern = Pattern.compile(REGEX);

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String url = "http://www.cjh.com.cn/sqindex.html";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        header.set("Host", "www.cjh.com.cn");
        header.set("Connection", "keep-alive");
        header.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Origin", "http://106.37.208.243:8068");
        header.set("Referer", "http://www.cjh.com.cn/swyb_sssq.html");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");


        HttpEntity<Object> entity = new HttpEntity<>(null, header);


        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.GET, entity);

        logger.info("ChangeJiang Task status code: {}", response.getStatusCode());

        List<ChangeJiang> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("ChangeJiang response.getBody() is null or \"\" ");
        else
            parse = parse(response.getBody());

        String s = save(parse);
        logger.info(s);
    }

    private List<ChangeJiang> parse(String body) {
        List<ChangeJiang> results = new ArrayList<>();

        Matcher matcher = pattern.matcher(body);
        String jsonStr = null;
        if (matcher.find()) {
            jsonStr = matcher.group(1);
            JSONArray data = (JSONArray) JSONObject.parse(jsonStr);

            for (Object datum : data) {
                JSONObject temp = (JSONObject) datum;

                ChangeJiang entity = new ChangeJiang();
                Field[] fields = entity.getClass().getDeclaredFields();

                for (Field field : fields) {
                    if (field.getAnnotation(BeanField.class) == null) continue;
                    field.setAccessible(true);
                    try {
                        field.set(entity, temp.get(field.getAnnotation(BeanField.class).property()) + "");
                    } catch (IllegalAccessException e) {
                        logger.error("ChangeJiang Field set value IllegalAccessException !!!");
                        e.printStackTrace();
                    }
                }
                entity.setCreateTime(DateUtils.formatDateTime(new Date()));
                results.add(entity);
            }
            logger.info("ChangeJiang converts results：{}", results.size());
        } else {
            logger.error("ChangeJiang Task HTML Regex match failed!!!");
        }
        return results;
    }

    private String save(List<ChangeJiang> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.CHANGE_JIANG_COMMITTEE.getValue(),
                EsConstants.TypeConstant.CHANGE_JIANG_COMMITTEE.getValue(), list);
    }

}
