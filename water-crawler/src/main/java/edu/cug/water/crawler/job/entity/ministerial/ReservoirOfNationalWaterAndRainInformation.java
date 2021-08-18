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
public class ReservoirOfNationalWaterAndRainInformation {
    @BeanField(property = "dateTime")
    private String dateTime;

    @BeanField(property = "poiAddv")
    private String province;

    @BeanField(property = "poiBsnm")
    private String watershed;

    @BeanField(property = "damel")
    private String crestLevel;

    @BeanField(property = "inq")
    private String reservoirInflow;

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

    // 库水位
    @BeanField(property = "rz")
    private String reservoirLevel;

    // 蓄水量
    @BeanField(property = "wl")
    private String storage;

    private String creatTime;

}
