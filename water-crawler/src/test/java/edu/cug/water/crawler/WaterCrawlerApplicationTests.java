package edu.cug.water.crawler;

import edu.cug.water.crawler.job.task.ministerial.NationalSurfaceWaterQuality;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WaterCrawlerApplicationTests {

    @Autowired
    NationalSurfaceWaterQuality nationalSurfaceWaterQuality;

    @Test
    void come(){
        try {
            nationalSurfaceWaterQuality.run(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
