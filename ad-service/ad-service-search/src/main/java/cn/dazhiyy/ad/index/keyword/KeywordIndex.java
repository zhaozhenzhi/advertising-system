package cn.dazhiyy.ad.index.keyword;

import cn.dazhiyy.ad.common.factory.ContainerFactory;
import cn.dazhiyy.ad.index.IndexAware;
import cn.dazhiyy.ad.index.IndexTableName;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index.keyword
 * @className KeywordIndex
 * @description TODO
 * @date 2019/3/19 14:01
 */
@Slf4j
@Component
@IndexTableName(name = "ad_unit_keyword")
public class KeywordIndex implements IndexAware<String, Set<Long>> {

    private Map<String, Set<Long>> keywordIndexMap = Maps.newConcurrentMap();

    private Map<Long,Set<String>> unitIndexMap = Maps.newConcurrentMap();

    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isBlank(key) || keywordIndexMap.isEmpty()) {
            return new ConcurrentSkipListSet<>();
        }
        return keywordIndexMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        Set<Long> unitIds = ContainerFactory.setorCreate(key, keywordIndexMap, ConcurrentSkipListSet::new);
        unitIds.addAll(value);
        //倒排索引
        for (Long unitId : unitIds) {
            Set<String> keywords = ContainerFactory.setorCreate(unitId, unitIndexMap, ConcurrentSkipListSet::new);
            keywords.add(key);
        }
    }

    @Override
    public void add(List<Map<String, String>> data) {

    }

    @Override
    public void update(String key, Set<Long> value) {
        throw new RuntimeException("不支持update操作");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        Set<Long> unitIds = ContainerFactory.setorCreate(key, keywordIndexMap, ConcurrentSkipListSet::new);
        unitIds.removeAll(value);
        //倒排索引删除
        for (Long unitId : unitIds) {
            Set<String> keywords = ContainerFactory.setorCreate(unitId, unitIndexMap, ConcurrentSkipListSet::new);
            keywords.remove(key);
        }
    }

    public Boolean macth(Long unitId,Set<String> keyword){
        if (unitIndexMap.get(unitId) != null && CollectionUtils.isNotEmpty(unitIndexMap.get(unitId))) {
            //判断是否匹配
            return CollectionUtils.isSubCollection(keyword,unitIndexMap.get(unitId));
        }
        return Boolean.FALSE;
    }
}
