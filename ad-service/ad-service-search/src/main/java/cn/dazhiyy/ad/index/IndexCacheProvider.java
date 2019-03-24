package cn.dazhiyy.ad.index;

import cn.dazhiyy.ad.cache.CacheProvider;
import cn.dazhiyy.ad.util.IndexUtil;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index
 * @className IndexCache
 * @description TODO
 * @date 2019/3/19 22:04
 */
@Slf4j
@Component
public class IndexCacheProvider implements PriorityOrdered, CacheProvider<IndexAware> {

    @Getter
    private final Map<Class,IndexAware> indexAwareClazzMap = Maps.newConcurrentMap();

    private final Map<String,Class<? extends IndexAware>> beanNameMap= Maps.newConcurrentMap();

    @Autowired
    public IndexCacheProvider(List<IndexAware> indexAwareList){
        for (IndexAware indexAware : indexAwareList) {
            Class<? extends IndexAware> iClass = indexAware.getClass();
            IndexTableName annotation = iClass.getAnnotation(IndexTableName.class);
            if (annotation == null) {
                continue;
            }
            String name = annotation.name();
            indexAwareClazzMap.put(iClass,indexAware);
            beanNameMap.put(name,iClass);
        }
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    @Override
    public IndexAware getIndex(Class clazz) {
        return indexAwareClazzMap.get(clazz);
    }

    @Override
    public IndexAware getIndex(String beanName) {
        return indexAwareClazzMap.get(beanNameMap.get(beanName));
    }


    public void op (String tableName,OpType opType,List<Map<String,String>> data){
        IndexAware index = getIndex(tableName);

        if (index == null ) {
            log.error("表{}为建立索引");
            return;
        }
        IndexUtil.handlerEvent(index,data,opType);
    }




}
