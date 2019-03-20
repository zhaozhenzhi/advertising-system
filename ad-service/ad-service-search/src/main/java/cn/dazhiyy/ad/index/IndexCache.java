package cn.dazhiyy.ad.index;

import cn.dazhiyy.ad.common.util.ApplicationContextUtil;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index
 * @className IndexCache
 * @description TODO
 * @date 2019/3/19 22:04
 */
@Component
public class IndexCache implements PriorityOrdered {

    @Getter
    private final Map<Class,IndexAware> indexAwareMap = Maps.newConcurrentMap();

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    public IndexAware getIndex(Class clazz) {
        IndexAware indexAware = indexAwareMap.get(clazz);
        if (indexAware == null) {
            IndexAware bean = ApplicationContextUtil.getBean(IndexAware.class);
            indexAwareMap.put(clazz,bean);
            return bean;
        }
        return indexAware;
    }

}
