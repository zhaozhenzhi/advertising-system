package cn.dazhiyy.ad.dao;

import cn.dazhiyy.ad.entity.AdUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.dao
 * @className AdUnitRespository
 * @description AdUnit表的持久层映射
 * @date 2019/3/17 10:53
 */
public interface AdUnitRespository extends JpaRepository<AdUnitEntity,Long> {
}
