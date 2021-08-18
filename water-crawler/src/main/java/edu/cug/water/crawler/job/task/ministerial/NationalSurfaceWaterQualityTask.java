package edu.cug.water.crawler.job.task.ministerial;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.cug.water.crawler.common.annotation.BeanField;
import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.ministerial.NationalSurfaceWaterQuality;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url：http://106.37.208.243:8068/GJZ/Business/Publish/Main.html
 * 1998个国控水站
 */
@Component("job:nationalSurfaceWaterQuality")
public class NationalSurfaceWaterQualityTask implements ITask {

    private static final String REGEX_STR = "title='所在地市:(.*);所属河流:(.*)'>(.*)</span>";
    private static final String REGEX_NUMBER = "title='原始值：(.*)'>";
    static final Pattern patternStr = Pattern.compile(REGEX_STR);
    static final Pattern patternNumber = Pattern.compile(REGEX_NUMBER);

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String url = "http://106.37.208.243:8068/GJZ/Ajax/Publish.ashx";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String params) throws IOException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        header.set("Host", "106.37.208.243:8068");
        header.set("Connection", "keep-alive");
        header.set("Content-Length", "68");
        header.set("Accept", "application/json, text/javascript, */*; q=0.01");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.set("Origin", "http://106.37.208.243:8068");
        header.set("Referer", "http://106.37.208.243:8068/GJZ/Business/Publish/RealDatas.html");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");

        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("AreaID", "");
        param.add("RiverID", "");
        param.add("MNName", "");
        param.add("PageIndex", "1");
        param.add("PageSize", "2000");
        param.add("action", "getRealDatas");


        HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(param, header);


        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.POST, entity);

        logger.info("NationalSurfaceWaterQuality Task status code: {}", response.getStatusCode());

        List<NationalSurfaceWaterQuality> parse = null;
        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("NationalSurfaceWaterQuality response.getBody() is null or \"\" ");
        else
            parse = parse(response.getBody());

        String save = save(parse);
        logger.info(save);
    }


    public List<NationalSurfaceWaterQuality> parse(String jsonStr) {
        List<NationalSurfaceWaterQuality> results = new ArrayList<>();

        JSONObject response = (JSONObject) JSONObject.parse(jsonStr);
        JSONArray data = (JSONArray) response.get("tbody");
        int records = response.getIntValue("records");
        for (int i = 0; i < data.size(); i++) {
            JSONArray items = data.getJSONArray(i);

            NationalSurfaceWaterQuality quality = new NationalSurfaceWaterQuality();

            Field[] declaredFields = quality.getClass().getDeclaredFields();

            List<Field> list = new ArrayList<Field>();
            for (Field field : declaredFields) {
                if (field.getAnnotation(BeanField.class) != null) list.add(field);
            }

            list.sort(Comparator.comparingInt(m -> m.getAnnotation(BeanField.class).order()));

            for (int j = 0; j < list.size(); j++) {
                try {
                    Field item = list.get(j);
                    item.setAccessible(true);
                    int index = item.getAnnotation(BeanField.class).dealNumber();
                    if (index != -1 && items.getString(j) != null) {
                        item.set(quality, methodIndex(items.getString(j), index));
                    } else {
                        item.set(quality, items.getString(j));
                    }
                } catch (IllegalAccessException e) {
                    logger.error("NationalSurfaceWaterQuality 实体转换出现问题！");
                    e.printStackTrace();
                }
            }
            quality.setCreateTime(DateUtils.formatDateTime(new Date()));
            results.add(quality);
        }
        logger.info("NationalSurfaceWaterQuality converts results：{}", results.size());
        return results;
    }

    public static String methodIndex(String str, int idx) {
        if (idx == 1) return dealString(str);
        if (idx == 2) return dealNumber(str);
        if (idx == 3) return dealDate(str);
        return null;
    }

    public static String dealString(String str) {
        Matcher matcher = patternStr.matcher(str.trim());
        if (matcher.find()) {
            str = matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
            str = str.replace("&#10", "");
        }
        return str.trim();
    }

    public static String dealNumber(String str) {
        Matcher matcher = patternNumber.matcher(str.trim());
        if (matcher.find()) {
            str = matcher.group(1);
        }
        return str.trim();
    }

    public static String dealDate(String str) {
        str = Calendar.getInstance().get(Calendar.YEAR) + "-" + str.trim();

        return str.trim();
    }

    public String save(List<NationalSurfaceWaterQuality> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.NATIONAL_SURFACE_WATER_QUALITY.getValue()
                , EsConstants.TypeConstant.NATIONAL_SURFACE_WATER_QUALITY.getValue(), list);
    }
}
