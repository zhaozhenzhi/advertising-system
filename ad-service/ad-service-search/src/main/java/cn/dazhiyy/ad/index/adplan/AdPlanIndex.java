package cn.dazhiyy.ad.index.adplan;

import cn.dazhiyy.ad.index.IndexAware;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index.adplan
 * @className AdplanIndex
 * @description TODO
 * @date 2019/3/19 10:35
 */
@Slf4j
public class AdPlanIndex implements IndexAware<Long,AdPlanIndexBean> {

    private Map<Long,AdPlanIndexBean> indexPlanIndex = Maps.newConcurrentMap();

    @Override
    public AdPlanIndexBean get(Long key) {
        if (indexPlanIndex.isEmpty() || key == null) {
            return null;
        }
        return indexPlanIndex.get(key);
    }

    @Override
    public void add(Long key, AdPlanIndexBean value) {
        indexPlanIndex.put(key,value);
    }

    @Override
    public void update(Long key, AdPlanIndexBean value) {
        AdPlanIndexBean adPlanIndexBean = indexPlanIndex.putIfAbsent(key, value);
        if (adPlanIndexBean != null) {
            adPlanIndexBean.update(value);
        }
    }

    @Override
    public void delete(Long key, AdPlanIndexBean value) {
        indexPlanIndex.remove(key);
    }
}
