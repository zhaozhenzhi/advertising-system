package cn.dazhiyy.ad.cache;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.cache
 * @className CacheProvider
 * @description TODO
 * @date 2019/3/22 13:38
 */
public interface CacheProvider<T> {

    /**
     * 通过class获得所以
     *
     * @param clazz
     * @return
     */
    T getIndex(Class<T> clazz);

    /**
     * 通过beanName拿取索引
     *
     * @param beanName
     * @return
     */
    T getIndex(String beanName);
}
