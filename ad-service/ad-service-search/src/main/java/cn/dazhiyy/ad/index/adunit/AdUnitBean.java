package cn.dazhiyy.ad.index.adunit;

import cn.dazhiyy.ad.index.adplan.AdPlanIndexBean;
import lombok.Data;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index.adunit
 * @className AdUnitBean
 * @description TODO
 * @date 2019/3/19 13:50
 */
@Data
public class AdUnitBean {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanIndexBean adPlanIndexBean;

    public void update(AdUnitBean adUnitBean){
        if (null != adUnitBean.getUnitId()) {
            this.unitId = adUnitBean.getUnitId();
        }
        if (null != adUnitBean.getUnitStatus()) {
            this.unitStatus = adUnitBean.getUnitStatus();
        }
        if (null != adUnitBean.getPositionType()) {
            this.positionType = adUnitBean.getPositionType();
        }
        if (null != planId) {
            this.planId = adUnitBean.getPlanId();
        }
        if (null != adUnitBean.getAdPlanIndexBean()) {
            this.adPlanIndexBean = adUnitBean.getAdPlanIndexBean();
        }
    }
}
