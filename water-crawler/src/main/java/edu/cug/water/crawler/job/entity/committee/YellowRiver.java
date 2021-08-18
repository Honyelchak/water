package edu.cug.water.crawler.job.entity.committee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class YellowRiver {

    private String date;

    private String createTime;

    private String riverName;

    private String stationName;

    private String waterStage;

    private String flow;

    //含沙量
    private String grittiness;
}
