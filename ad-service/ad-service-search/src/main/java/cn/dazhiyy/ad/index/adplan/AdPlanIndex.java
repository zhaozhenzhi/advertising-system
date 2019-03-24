package cn.dazhiyy.ad.index.adplan;

import cn.dazhiyy.ad.index.IndexAware;
import cn.dazhiyy.ad.index.IndexTableName;
import cn.dazhiyy.ad.util.SearchUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
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
@Component
@IndexTableName(name = "ad_plan")
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
    public void add(List<Map<String, String>> data) {
        Class<AdPlanIndexBean> clazz = AdPlanIndexBean.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Map<String, String> datum : data) {
            try {
                AdPlanIndexBean adPlanIndexBean = clazz.newInstance();
                for (Map.Entry<String, String> entry : datum.entrySet()) {
                    for (Field declaredField : declaredFields) {
                        String name = declaredField.getName();
                        if (name.equals(SearchUtil.UnderlineToHump(entry.getKey()))) {
                            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
                            Method getMethod = clazz.getMethod("get" + methodName);
                            Class<?> returnType = getMethod.getReturnType();
                            Method setMethod = clazz.getMethod("set" + methodName, returnType);

                            if (returnType.equals(Integer.class)) {
                                //该字段是整形
                                setMethod.invoke(adPlanIndexBean, Integer.parseInt(entry.getValue()));
                            } else if (returnType.equals(Long.class)) {
                                setMethod.invoke(adPlanIndexBean, Long.parseLong(entry.getValue()));
                            } else if (returnType.equals(String.class)) {
                                setMethod.invoke(adPlanIndexBean, entry.getValue());
                            } else if (returnType.equals(Date.class)) {
                                setMethod.invoke(adPlanIndexBean, SearchUtil.parseStringDate(entry.getValue()));
                            }

                        }
                    }
                }
                indexPlanIndex.put(adPlanIndexBean.getId(),adPlanIndexBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
