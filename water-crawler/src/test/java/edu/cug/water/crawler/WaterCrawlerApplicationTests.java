package edu.cug.water.crawler;

import edu.cug.water.crawler.job.task.ministerial.NationalSurfaceWaterQualityTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WaterCrawlerApplicationTests {

    @Autowired
    NationalSurfaceWaterQualityTask nationalSurfaceWaterQualityTask;

    @Test
    void come(){
        try {
            nationalSurfaceWaterQualityTask.run(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
