package cn.dazhiyy.ad.index;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index
 * @className IndexAware
 * @description TODO
 * @date 2019/3/18 22:26
 */
public interface IndexAware<K,V> {

    /**
     * 获得索引
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 添加索引
     *
     * @param key
     * @param value
     */
    void add(K key,V value);

    /**
     * 更新索引
     *
     * @param key
     * @param value
     */
    void update(K key,V value);

    /**
     * 删除索引
     *
     * @param key
     */
    void delete(K key,V value);
}
