package edu.cug.water.crawler.job.task.committee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.HaiheRiver;
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
import java.util.*;

@Component("job:HaiheRiver")
public class HaiheRiverTask implements ITask {

    private static final String url = "http://www.hwcc.gov.cn/hwccapi/ysq/selectApi";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EsUtils esUtils;

    @Autowired
    RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.set("Host", "www.hwcc.gov.cn");
        header.set("Connection", "keep-alive");
        header.set("Accept", "application/json, text/plain, */*");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Referer", "http://www.hwcc.gov.cn/hwccapp/lyysq");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");


        HttpEntity<Object> entity = new HttpEntity<>(null, header);


        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.GET, entity);

        logger.info("HaiheRiver Task status code: {}", response.getStatusCode());

        List<HaiheRiver> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("HaiheRiver response.getBody() is null or \"\" ");
        else {
            JSONObject jsonObject = (JSONObject) JSONObject.parse(response.getBody());
            int status = jsonObject.getIntValue("status");
            String msg = jsonObject.getString("message");
            logger.info("HaiheRiver Request Data status: {}, {}", status, msg);

            JSONObject data = jsonObject.getJSONObject("data").getJSONObject("data");
            Set<String> keySet = new TreeSet<>(data.keySet());
            int i = 1;
            for (String key : keySet) {
                JSONArray jsonArray = data.getJSONArray(key);
                List<Map<String, String>> list = parse(jsonArray, i ++, key);
                String save = save(list, key.toLowerCase());
                logger.info(save);
            }
        }

    }


    private List<Map<String, String>> parse(JSONArray data, int categoryIdx, String category) {
        List<Map<String, String>> results = new ArrayList<>();

        for (Object datum : data) {
            JSONObject temp = (JSONObject) datum;
            HaiheRiver entity = new HaiheRiver();
            Map<String, String> map = new HashMap<>();
            Field[] fields = entity.getClass().getDeclaredFields();

            for (Field field : fields) {
                BeanField annotation = field.getAnnotation(BeanField.class);

                if (annotation == null) continue;
                if (annotation.belongs() != 0) {
                    if (annotation.belongs() == -categoryIdx) continue;
                    if (annotation.belongs() != categoryIdx) continue;
                }

                map.put(field.getName(), temp.get(annotation.property()) + "" .trim());
            }
            map.put("createTime", DateUtils.formatDateTime(new Date()));
            results.add(map);
        }
        logger.info("HaiheRiver {} converts results：{}", category, results.size());
        return results;
    }

    private String save(List<Map<String, String>> list, String key) {
        return esUtils.addDocuments(EsConstants.TypeConstant.HAIHE_RIVER_COMMITTEE.getValue() + "_" + key,
                EsConstants.TypeConstant.HAIHE_RIVER_COMMITTEE.getValue() + "_" + key, list);
    }
}
