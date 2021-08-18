package edu.cug.water.crawler.job.task.committee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.TaiHuWater;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("job:TaiHuWater")
public class TaiHuWaterTask implements ITask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String url = "http://www.tba.gov.cn/TrueCMS/gatewayController/gatewayExempts.do";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.set("Host", "www.tba.gov.cn");
        header.set("Connection", "keep-alive");
        header.set("Accept", "application/json, text/javascript, */*; q=0.01");
        header.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Origin", "http://www.tba.gov.cn");
        header.set("Referer", "http://www.tba.gov.cn/");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");


        String param = "LY=Get_Water&TM=" + DateUtils.format(new Date());

        HttpEntity<String> entity = new HttpEntity<String>(param, header);

        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.POST, entity);

        logger.info("TaiHuWater Task status code: {}", response.getStatusCode());

        List<TaiHuWater> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("TaiHuWater response.getBody() is null or \"\" ");
        else
            parse = parse(response.getBody());

        String s = save(parse);
        logger.info(s);
    }

    private List<TaiHuWater> parse(String body) {

        logger.info(body);
        List<TaiHuWater> results = new ArrayList<>();

        JSONObject parse = (JSONObject) JSONObject.parse(body);
        JSONArray jsonArray = parse.getJSONArray("succes");

        for (Object datum : jsonArray) {
            JSONObject temp = (JSONObject) datum;
            if (temp.get("choice") != null) continue;
            TaiHuWater entity = new TaiHuWater();
            Field[] fields = entity.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.getAnnotation(BeanField.class) == null) continue;
                field.setAccessible(true);
                try {
                    field.set(entity, temp.get(field.getAnnotation(BeanField.class).property()) + "");
                } catch (IllegalAccessException e) {
                    logger.error("TaiHuWater Field set value IllegalAccessException !!!");
                    e.printStackTrace();
                }
            }
            entity.setCreateTime(DateUtils.formatDateTime(new Date()));
            results.add(entity);
        }
        logger.info("TaiHuWater converts results：{}", results.size());

        return results;
    }

    private String save(List<TaiHuWater> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.TAI_HU_WATER.getValue(),
                EsConstants.TypeConstant.TAI_HU_WATER.getValue(), list);
    }
}