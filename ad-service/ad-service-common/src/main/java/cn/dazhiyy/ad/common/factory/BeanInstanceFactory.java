package cn.dazhiyy.ad.common.factory;

import java.util.function.Supplier;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.factory
 * @className BeanInstanceFactory
 * @description TODO
 * @date 2019/3/19 21:32
 */
public class BeanInstanceFactory {

    /**
     * 返回bean的实例
     * @param factory 工厂
     * @param <V>
     * @return
     */
    public static <V> V newInstance(Supplier<V> factory){
        return factory.get();
    }
}
