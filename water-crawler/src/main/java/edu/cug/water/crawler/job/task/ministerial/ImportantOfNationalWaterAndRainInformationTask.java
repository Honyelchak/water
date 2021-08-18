package edu.cug.water.crawler.job.task.ministerial;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.ministerial.ImportantOfNationalWaterAndRainInformation;
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

@Component("job:ImportantOfNationalWaterAndRainInformation")
public class ImportantOfNationalWaterAndRainInformationTask implements ITask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String url = "http://xxfb.mwr.cn/hydroSearch/pointHydroInfo";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "xxfb.mwr.cn");
        headers.set("Connection", "keep-alive");
        headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.set("X-Requested-With", "XMLHttpRequest");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        headers.set("Content-Type", "application/json");
        headers.set("Referer", "http://xxfb.mwr.cn/sq_zdysq.html");
        headers.set("Accept-Encoding", "gzip, deflate");
        headers.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        headers.set("Cookie", "__FT10000066=2021-7-2-21-1-50; __NRU10000066=1625230910440; __REC10000066=4; __RT10000066=2021-8-16-20-50-10; zhuzhan=97700904");


        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.POST, entity);

        logger.info("ImportantOfNationalWaterAndRainInformation Task status code: {}", response.getStatusCode());

        List<ImportantOfNationalWaterAndRainInformation> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("ImportantOfNationalWaterAndRainInformation response.getBody() is null or \"\" ");
        else
            parse = parse(response.getBody());

        String save = save(parse);
        logger.info(save);

    }

    private String save(List<ImportantOfNationalWaterAndRainInformation> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.IMPORTANT_OF_NATIONAL_WATER_AND_RAIN_INFORMATION.getValue(),
                EsConstants.TypeConstant.IMPORTANT_OF_NATIONAL_WATER_AND_RAIN_INFORMATION.getValue(), list);
    }

    private List<ImportantOfNationalWaterAndRainInformation> parse(String body) {
        List<ImportantOfNationalWaterAndRainInformation> results = new ArrayList<>();

        JSONObject response = (JSONObject)JSONObject.parse(body);

        logger.info("response data: message {}", response.get("message"));

        JSONObject result = response.getJSONObject("result");
        JSONArray data = result.getJSONArray("data");

        for (Object item : data) {
            JSONObject temp = (JSONObject) item;

            ImportantOfNationalWaterAndRainInformation entity = new ImportantOfNationalWaterAndRainInformation();
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(BeanField.class) == null) continue;
                String property = field.getAnnotation(BeanField.class).property();
                field.setAccessible(true);
                try {
                    field.set(entity, temp.get(property) + "");
                } catch (IllegalAccessException e) {
                    logger.error("ImportantOfNationalWaterAndRainInformation Task parse 出错！");
                    e.printStackTrace();
                }
            }
            entity.setCreatTime(DateUtils.formatDateTime(new Date()));
            results.add(entity);
        }
        logger.info("ImportantOfNationalWaterAndRainInformation converts results：{}", results.size());
        return results;
    }

}
