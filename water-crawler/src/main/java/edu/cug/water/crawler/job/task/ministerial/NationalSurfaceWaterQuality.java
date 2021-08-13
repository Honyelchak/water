package edu.cug.water.crawler.job.task.ministerial;

import edu.cug.water.crawler.job.task.ITask;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * url：http://106.37.208.243:8068/GJZ/Business/Publish/Main.html
 * 1998个国控水站
 */
@Component("job:nationalSurfaceWaterQuality")
public class NationalSurfaceWaterQuality implements ITask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void run(String params) throws IOException {

        boolean ping = restHighLevelClient.ping(RequestOptions.DEFAULT);
        System.out.println(ping);
    }
}
