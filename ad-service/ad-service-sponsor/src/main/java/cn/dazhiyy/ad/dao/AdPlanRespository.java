package cn.dazhiyy.ad.dao;

import cn.dazhiyy.ad.entity.AdPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.dao
 * @className AdPlanRespository
 * @description AdPlan表的持久层映射
 * @date 2019/3/17 10:52
 */
public interface AdPlanRespository extends JpaRepository<AdPlanEntity,Long> {

}
