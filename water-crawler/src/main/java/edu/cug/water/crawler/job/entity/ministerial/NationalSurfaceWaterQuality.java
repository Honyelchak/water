package edu.cug.water.crawler.job.entity.ministerial;


import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class NationalSurfaceWaterQuality {


    //"省份"
    @BeanField(order = 1)
    private String province;

    //"流域"
    @BeanField(order = 2)
    private String watershed;

    //"断面名称"
    @BeanField(order = 3, dealNumber = 1)
    private String croSectionOfRiver;

    //"监测时间"
    @BeanField(order = 4, dealNumber = 3)
    private String monitoringTime;

    //"水质类别"
    @BeanField(order = 5)
    private String level;

    //"水温<br/><span class='unit'>(℃)</span>"
    @BeanField(order = 6, dealNumber = 2)
    private String temperature;

    //"pH<br/><span class='unit'>(无量纲)</span>"
    @BeanField(order = 7, dealNumber = 2)
    private String ph;

    //"溶解氧<br/><span class='unit'>(mg/L)</span>"
    @BeanField(order = 8, dealNumber = 2)
    private String dissolvedOxygen;

    //"电导率<br/><span class='unit'>(μS/cm)</span>"
    @BeanField(order = 9, dealNumber = 2)
    private String conductivity;

    //"浊度<br/><span class='unit'>(NTU)</span>"
    @BeanField(order = 10, dealNumber = 2)
    private String turbidity;

    // "高锰酸盐指数<br/><span class='unit'>(mg/L)</span>"
    @BeanField(order = 11, dealNumber = 2)
    private String codmn;

    // "氨氮<br/><span class='unit'>(mg/L)</span>"
    @BeanField(order = 12, dealNumber = 2)
    private String ammonia;

    // "总磷<br/><span class='unit'>(mg/L)</span>"
    @BeanField(order = 13, dealNumber = 2)
    private String phosphorus;

    // "总氮<br/><span class='unit'>(mg/L)</span>"\
    @BeanField(order = 14, dealNumber = 2)
    private String nitrogen;

    // "叶绿素α<br/><span class='unit'>(mg/L)</span>"
    @BeanField(order = 15, dealNumber = 2)
    private String chlorophyll;

    // "藻密度<br/><span class='unit'>(cells/L)</span>"
    @BeanField(order = 16, dealNumber = 2)
    private String algalDensity;

    // "站点情况"
    @BeanField(order = 17)
    private String status;

    private String createTime;
}
