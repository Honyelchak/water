package edu.cug.water.crawler.job.entity.committee;

import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SongLiaoWater {


    private String stationName;

    private String time;

    private String waterStage;

    private String flow;

    @BeanField(dealNumber = 1)
    private String fluctuation;

    private String createTime;
}
