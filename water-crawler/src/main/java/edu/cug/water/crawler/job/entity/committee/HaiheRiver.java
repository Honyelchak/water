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
public class HaiheRiver {

    /**
     * hd
     * AVQ: 288
     * HNNM: "蓟运河                        "
     * RVNM: "泃河                          "
     * STNM: "罗庄子                        "
     * TM: "2021-08-17 08"
     * WPTN: "6"
     * Z: 17.92
     */

    @BeanField(property = "HNNM")
    private String watershed;

    @BeanField(property = "STNM")
    private String stationName;

    @BeanField(property = "RVNM", belongs = -2)
    private String riverName;

    @BeanField(property = "TM")
    private String time;

    /////////////////////////////////////////////////////////






    @BeanField(property = "WPTN", belongs = 1)
    private String warningCode;

    @BeanField(property = "AVQ", belongs = 1)
    private String averageFlow;

    @BeanField(property = "Z", belongs = 1)
    private String waterStage;



    /**
     * todayRain
     * DYP: 65.3
     * HNNM: "大清河                        "
     * STNM: "调节闸闸上                    "
     * TM: "2021-08-17 08"
     */
    @BeanField(property = "DYP", belongs = 2)
    private String dailyPrecipitation;

    /**
     * AVINQ: 287
     * AVOTQ: 2.45
     * HNNM: "潮白河                        "
     * RVNM: "白河                          "
     * RZ: 153.44
     * STNM: "密云水库"
     * TM: "2021-08-17 08"
     * W: 3269.1
     */
    @BeanField(property = "AVINQ", belongs = 3)
    private String averageInFlow;

    @BeanField(property = "AVOTQ", belongs = 3)
    private String averageOutFlow;

    // 水位:m
    @BeanField(property = "RZ", belongs = 3)
    private String reservoirStage;

    //蓄量:百万立方米
    @BeanField(property = "W", belongs = 3)
    private String capacity;

    private String createTime;
}
