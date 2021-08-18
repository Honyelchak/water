package edu.cug.water.crawler.job.task.ministerial;

import edu.cug.water.crawler.common.utils.DateUtils;
import edu.cug.water.crawler.job.task.committee.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class NationalSurfaceWaterQualityTaskTest {

    @Autowired
    NationalSurfaceWaterQualityTask nationalSurfaceWaterQualityTask;

    @Autowired
    RiversOfNationalWaterAndRainInformationTask riversOfNationalWaterAndRainInformationTask;

    @Autowired
    ImportantOfNationalWaterAndRainInformationTask importantOfNationalWaterAndRainInformationTask;

    @Autowired
    ReservoirOfNationalWaterAndRainInformationTask reservoirOfNationalWaterAndRainInformationTask;

    @Autowired
    HaiheRiverTask haiheRiverTask;

    @Autowired
    PearlWaterTask pearlWaterTask;


    @Autowired
    SongLiaoWaterTask songLiaoWaterTask;

    @Autowired
    TaiHuWaterTask taiHuWaterTask;

    @Autowired
    YellowRiverTask yellowRiverTask;

    @Test
    void come(){
        try {
           // yellowRiverTask.run(null);
            yellowRiverTask.run("2021-08-03");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test()
    {
        String s = DateUtils.formatDateTime(new Date());
        System.out.println(s);
    }
}