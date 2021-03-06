package cn.dazhiyy.ad.index.adunit;

import cn.dazhiyy.ad.index.IndexAware;
import cn.dazhiyy.ad.index.IndexTableName;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.index.adunit
 * @className AdUnitIndex
 * @description TODO
 * @date 2019/3/19 13:52
 */
@Component
@IndexTableName(name = "ad_unit")
public class AdUnitIndex implements IndexAware<Long,AdUnitBean> {

    private Map<Long,AdUnitBean> adUnitBeanMap = Maps.newConcurrentMap();

    @Override
    public AdUnitBean get(Long key) {
        if (key == null || adUnitBeanMap.isEmpty()) {
            return null;
        }
        return adUnitBeanMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitBean value) {
        adUnitBeanMap.put(key,value);
    }

    @Override
    public void add(List<Map<String, String>> data) {

    }

    @Override
    public void update(Long key, AdUnitBean value) {
        AdUnitBean adUnitBean = adUnitBeanMap.putIfAbsent(key, value);
        if (adUnitBean != null) {
            adUnitBean.update(value);
        }
    }

    @Override
    public void delete(Long key, AdUnitBean value) {
        adUnitBeanMap.remove(key);
    }


}
