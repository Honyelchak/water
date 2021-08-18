package edu.cug.water.crawler.job.task.committee;


import edu.cug.water.crawler.common.constant.EsConstants;
import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.common.utils.EsUtils;
import edu.cug.water.crawler.job.entity.committee.YellowRiver;
import edu.cug.water.crawler.job.request.RequestUtils;
import edu.cug.water.crawler.job.task.ITask;
import org.frameworkset.util.LinkedMultiValueMap;
import org.frameworkset.util.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("job:YellowRiver")
public class YellowRiverTask implements ITask {

    private static final String REGEX = "<td>(.*?)</td>";
    private static final String REGEX_PARAM = "<input type=\"hidden\" name=\"(.*)\" id=\"(.*)\" value=\"(.*)\"";
    private static Pattern pattern = Pattern.compile(REGEX);
    private static Pattern pattern_param = Pattern.compile(REGEX_PARAM);


    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String url = "http://61.163.88.227:8006/hwsq.aspx?sr=0nkRxv6s9CTRMlwRgmfFF6jTpJPtAv87";

    @Autowired
    private EsUtils esUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Override
    public void run(String date) throws IOException {

        String index = requestIndex();

        List<YellowRiver> data = null;

        if (date == null || "".equals(date) || DateUtils.format(new Date()).equals(date.trim())) {
            date = DateUtils.format(new Date());
            data = parseDate(index, date);
        } else {
            Map<String, String> paramFromIndex = getParamFromIndex(index);
            String postData = requestPOST(paramFromIndex, date.trim());
            data = parseDate(postData, date);
        }
        String s = save(data);
        logger.info(s);
    }

    private Map<String, String> getParamFromIndex(String index) {
        Matcher matcher = pattern_param.matcher(index);
        Map<String, String> map = new HashMap<>();

        while(matcher.find()){
            if(matcher.groupCount() != 3)
                logger.error("YellowRiverTask GetParamFromIndex 正则失败！");
            map.put(matcher.group(1), matcher.group(3));
        }
        return map;
    }

    private List<YellowRiver> parseDate(String index, String date) {
        Matcher matcher = pattern.matcher(index);

        List<YellowRiver> results = new ArrayList<>();

        List<String> list = new ArrayList<>();
        while(matcher.find()) {
            list.add(matcher.group(1).trim());
        }

        for (int i = 0, base = 5; i < list.size() / base; i ++) {
            YellowRiver yellowRiver = new YellowRiver();
            yellowRiver.setRiverName(list.get(i * base)).
                    setStationName(list.get(i * base + 1)).
                    setWaterStage(list.get(i * base + 2)).
                    setFlow(list.get(i * base + 3)).
                    setGrittiness(list.get(i * base + 4)).
                    setCreateTime(DateUtils.formatDateTime(new Date())).
                    setDate(date);

            results.add(yellowRiver);
        }
        logger.info("YellowRiverTask Data Convert Results: {}", results.size());

        return results;
    }

    private String requestIndex() {
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

        logger.info("YellowRiver Task status code: {}", response.getStatusCode());

        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("YellowRiver response.getBody() is null or \"\" ");
        else {
            return response.getBody();
        }
        return "";
    }

    private String requestPOST(Map<String, String> paramFromIndex, String date) {
        HttpHeaders header = new HttpHeaders();

        header.set("Host", "61.163.88.227:8006");
        header.set("Connection", "keep-alive");
        header.set("Cache-Control", "no-cache");
        //header.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.set("Accept", "*/*");
        header.set("X-Requested-With", "XMLHttpRequest");
        header.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        header.set("Origin", "http://61.163.88.227:8006");
        header.set("Referer", "http://61.163.88.227:8006/hwsq.aspx?sr=0nkRxv6s9CTRMlwRgmfFF6jTpJPtAv87");
        header.set("Accept-Encoding", "gzip, deflate");
        header.set("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        header.set("X-MicrosoftAjax", "Delta=true");


        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.setAll(paramFromIndex);
        map.set("ctl00$ScriptManager1", "ctl00$ScriptManager1|ctl00$ContentLeft$Button1");
        map.set("ctl00$ContentLeft$menuDate1$TextBox11", date);
        map.set("__ASYNCPOST","true");
        map.set("ctl00$ContentLeft$Button1", "查询");

        HttpEntity<MultiValueMap> entity = new HttpEntity<>(map, header);

        ResponseEntity<String> response = requestUtils.request(url, HttpMethod.POST, entity);

        logger.info("YellowRiver Task status code: {}", response.getStatusCode());

        if ("".equals(response.getBody()) || response.getBody() == null)
            logger.info("YellowRiver response.getBody() is null or \"\" ");
        else {
            return response.getBody();
        }
        return "";
    }

    private String save(List<YellowRiver> list) {
        return esUtils.addDocuments(EsConstants.TypeConstant.YELLOW_RIVER.getValue(),
                EsConstants.TypeConstant.YELLOW_RIVER.getValue(), list);
    }
}
