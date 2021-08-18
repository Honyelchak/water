package edu.cug.water.crawler.job.task.committee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.pearl.ReservoirOfPearlWater;
import edu.cug.water.crawler.job.entity.committee.pearl.RiverOfPearlWater;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.apache.commons.lang.ArrayUtils;
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

@Component("job:PearlWater")
public class PearlWaterTask implements ITask {



    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        String url = "http://www.pearlwater.gov.cn/switch/" + params.trim() + "/list?userName=webuser&password=90E1x6C92B";
        if ("river".equals(params) || "rsvr".equals(params)) {
            request(url, params);
        } else {
            logger.error("PearlWaterTask Param is wrong: '{}'", params);
        }
    }

    public void request(String url, String params) {

        HttpHeaders header = new HttpHeaders();

        header.set("Host", "www.pearlwater.gov.cn");
        header.set("Connection", "keep-alive");
        header.set("Accept", "application/json, text/javascript, */*; q=0.01");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Origin", "http://www.pearlwater.gov.cn");
        header.set("Referer", "http://www.pearlwater.gov.cn/sssq/");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");

        HttpEntity<Object> entity = new HttpEntity<>(null, header);


        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.POST, entity);

        logger.info("PearlWater {} Task status code: {}", params, response.getStatusCode());

        List list = null;
        try {
            list = parse(params, response.getBody());
            String save = save(list, params);
            logger.info(save);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private List parse(String param, String body) throws IllegalAccessException, InstantiationException {
        JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        int status = jsonObject.getIntValue("code");
        String msg = jsonObject.getString("msg");
        logger.info("PearlWater {} Request Data status: {}, {}", param, status, msg);

        List results = new ArrayList<>();

        JSONArray data = jsonObject.getJSONArray("data");
        for (Object datum : data) {
            JSONObject temp = (JSONObject)datum;
            Class<?> aClass = null;
            if ("river".equals(param)) {
                aClass = RiverOfPearlWater.class;
            } else if ("rsvr".equals(param)) {
                aClass = ReservoirOfPearlWater.class;
            }
            Object o = aClass.newInstance();
            Field[] old = aClass.getDeclaredFields();

            Field[] fields = (Field[]) ArrayUtils.addAll(old, aClass.getSuperclass().getDeclaredFields());
            for (Field field : fields) {
                BeanField annotation = field.getAnnotation(BeanField.class);
                field.setAccessible(true);

                if (annotation == null) field.set(o, DateUtils.formatDateTime(new Date()));
                else field.set(o, (temp.get(annotation.property()) + "").trim());
            }
            results.add(o);
        }
        logger.info("PearlWater {} Task converts results：{}", param, results.size());
        return results;
    }

    private String save(List list, String param) {
        return esUtils.addDocuments(EsConstants.TypeConstant.PEARL_WATER_.getValue() + param.toLowerCase(),
                EsConstants.TypeConstant.PEARL_WATER_.getValue() + param.toLowerCase(), list);
    }
}
