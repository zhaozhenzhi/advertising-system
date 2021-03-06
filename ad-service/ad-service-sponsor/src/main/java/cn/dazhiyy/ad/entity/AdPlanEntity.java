package cn.dazhiyy.ad.entity;

import cn.dazhiyy.ad.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.entity
 * @className AdPlan
 * @description TODO
 * @date 2019/3/17 10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_plan")
public class AdPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Basic
    @Column(name = "plan_status", nullable = false)
    private Integer planStatus;

    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdPlanEntity(Long userId, String planName,
                        Date startDate, Date endDate) {
        this.userId = userId;
        this.planName = planName;
        this.planStatus = CommonStatus.VALID.getCode();
        this.startDate = startDate;
        this.endDate = endDate;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
