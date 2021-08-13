package edu.cug.water.crawler.job.task.ministerial;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class NationalSurfaceWaterQualityTest {

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