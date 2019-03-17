package cn.dazhiyy.ad.dao;

import cn.dazhiyy.ad.entity.AdUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.dao
 * @className AdUserRespository
 * @description AdUser持久层数据表接口
 * @date 2019/3/17 10:48
 */

public interface AdUserRespository extends JpaRepository<AdUserEntity,Long> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    AdUserEntity findByUsername(String username);
}
