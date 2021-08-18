package edu.cug.water.crawler.job.entity.committee.pearl;

import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.Data;

@Data
public class PearlWater {

    @BeanField(property = "stcd")
    private String stationCode;

    @BeanField(property = "stnm")
    private String stationName;

    @BeanField(property = "hnnm")
    private String hnnm;

    @BeanField(property = "stlc")
    private String stationLocation;

    @BeanField(property = "tm")
    private String time;

    @BeanField(property = "rvnm")
    private String riverName;

    @BeanField(property = "bsnm")
    private String bsnm;

    @BeanField(property = "lgtd")
    private String longitude;

    @BeanField(property = "lttd")
    private String latitude;

    private String createTime;

}
