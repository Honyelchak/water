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
public class RiverOfPearlWater extends PearlWater{

    @BeanField(property = "z")
    private String waterStage;

    @BeanField(property = "wrz")
    private String warningStage;

    @BeanField(property = "q")
    private String flow;

    @BeanField(property = "wrq")
    private String warningFlow;

}
