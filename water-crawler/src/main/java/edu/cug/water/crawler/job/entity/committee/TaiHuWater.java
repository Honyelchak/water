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
public class TaiHuWater {

  @BeanField(property = "GRZ")
  private String GRZ;

  @BeanField(property = "STCD")
  private String stationCode;

  @BeanField(property = "STNM")
  private String stationName;

  @BeanField(property = "TIME")
  private String time;

  @BeanField(property = "WRZ")
  private String warningStage;

  @BeanField(property = "Z")
  private String waterStage;

  private String createTime;

}
