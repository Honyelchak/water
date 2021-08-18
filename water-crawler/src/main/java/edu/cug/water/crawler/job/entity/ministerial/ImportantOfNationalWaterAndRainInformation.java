package edu.cug.water.crawler.job.entity.ministerial;

import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ImportantOfNationalWaterAndRainInformation {

    @BeanField(property = "dateTime")
    private String dateTime;

    @BeanField(property = "dyp")
    private String dailyPrecipitation;

    @BeanField(property = "lat")
    private String latitude;

    @BeanField(property = "lgt")
    private String longitude;

    @BeanField(property = "poiAddv")
    private String province;

    @BeanField(property = "poiBsnm")
    private String watershed;

    @BeanField(property = "rvnm")
    private String riverName;

    @BeanField(property = "stcd")
    private String stationCode;

    @BeanField(property = "stnm")
    private String stationName;

    @BeanField(property = "tm")
    private String time;

    @BeanField(property = "webStlc")
    private String stationLocation;

    //{{if wth=="5"}}雪
    //{{else wth=="6"}}雨夹雪
    //{{else wth=="7"}}雨
    //{{else wth=="8"}}阴
    //{{else wth=="9"}}晴
    //{{else}}--
    //{{/if}}
    @BeanField(property = "wl")
    private String weather;

    private String creatTime;


}
