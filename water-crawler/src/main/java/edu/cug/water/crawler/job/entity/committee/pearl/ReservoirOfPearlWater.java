package edu.cug.water.crawler.job.entity.committee.pearl;

import edu.cug.water.crawler.common.annotation.BeanField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ReservoirOfPearlWater extends PearlWater{


     @BeanField(property = "rz")
     private String reservoirStage;

     @BeanField(property = "fsltdz")
     private String fsltdz;

     @BeanField(property = "normz")
     private String normz;

     @BeanField(property = "w")
     private String storage;

     @BeanField(property = "rwchrcd")
     private String rwchrcd;

     @BeanField(property = "ddz")
     private String ddz;

     @BeanField(property = "actcp")
     private String actcp;

     @BeanField(property = "bgmd")
     private String bgmd;

     @BeanField(property = "dsflz")
     private String dsflz;

     @BeanField(property = "inq")
     private String inFlow;

     @BeanField(property = "otq")
     private String outFlow;

     @BeanField(property = "rwptn")
     private String rwptn;

     @BeanField(property = "ddcp")
     private String ddcp;

     @BeanField(property = "moditime")
     private String modifyTime;

     @BeanField(property = "edmd")
     private String edmd;
}
