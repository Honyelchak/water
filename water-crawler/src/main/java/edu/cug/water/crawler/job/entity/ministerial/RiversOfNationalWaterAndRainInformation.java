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
public class RiversOfNationalWaterAndRainInformation {

    @BeanField(property = "dateTime")
    private String dateTime;

    @BeanField(property = "poiAddv")
    private String province;

    @BeanField(property = "poiBsnm")
    private String watershed;

    @BeanField(property = "ql")
    private String flow;

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

    @BeanField(property = "wrz")
    private String warningWaterStage;

    @BeanField(property = "zl")
    private String waterStage;

    private String creatTime;
}
