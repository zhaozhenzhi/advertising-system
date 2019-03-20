package cn.dazhiyy.ad.index.adplan;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index.adplan
 * @className AdPlanIndexBean
 * @description 广告计划索引
 * @date 2019/3/19 10:35
 */
@Data
public class AdPlanIndexBean {

    private Long id;

    private Long userId;

    private String planStatus;

    private Date startDate;

    private Date endDate;

    public void update(AdPlanIndexBean adPlanIndexBean){
        if (userId == null || !userId.equals(adPlanIndexBean.getUserId())) {
            this.userId = adPlanIndexBean.getUserId();
        }

        if (StringUtils.isBlank(planStatus) || !planStatus.equals(adPlanIndexBean.getPlanStatus())) {
            this.planStatus = adPlanIndexBean.getPlanStatus();
        }

        if (startDate == null || !startDate.equals(adPlanIndexBean.getStartDate())) {
            this.startDate = adPlanIndexBean.getStartDate();
        }

        if (endDate == null || !endDate.equals(adPlanIndexBean.getEndDate())) {
            this.endDate = adPlanIndexBean.getEndDate();
        }
    }

}
