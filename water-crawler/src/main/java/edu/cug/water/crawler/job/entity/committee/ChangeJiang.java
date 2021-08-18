package edu.cug.water.crawler.job.entity.committee;

import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ChangeJiang {

    /**
     * oq: "0"
     * q: "11700"
     * rvnm: "长江"
     * stcd: "60105400"
     * stnm: "寸滩"
     * tm: 1629165600000
     * wptn: "4"
     * z: "164.67"
     */
    @BeanField(property = "oq")
    private String outFlow;

    //流量(m3/s)
    @BeanField(property = "q")
    private String flow;

    @BeanField(property = "rvnm")
    private String riverName;

    @BeanField(property = "stcd")
    private String stationCode;

    @BeanField(property = "stnm")
    private String stationName;

    @BeanField(property = "tm")
    private String time;

//    if(sssqTmp.wptn == 4) {
//        // 落
//        wptnColor = '#009900';
//    } else if(sssqTmp.wptn == 5) {
//        // 涨
//        wptnColor = '#ff0000';
//    }
    @BeanField(property = "wptn")
    private String warningCode;

    // m
    @BeanField(property = "z")
    private String waterStage;


    private String createTime;
}
