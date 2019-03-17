package cn.dazhiyy.ad.dao;

import cn.dazhiyy.ad.entity.CreativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.dao
 * @className CreativeRespository
 * @description TODO
 * @date 2019/3/17 10:56
 */
public interface CreativeRespository extends JpaRepository<CreativeEntity,Long> {
}
