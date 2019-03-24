package cn.dazhiyy.ad.util;

import cn.dazhiyy.ad.index.IndexAware;
import cn.dazhiyy.ad.index.OpType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.handler
 * @className AdLevelDataHandler
 * @description TODO
 * @date 2019/3/22 14:04
 */
@Slf4j
public class IndexUtil {


    public static  <K,V> void  handlerEvent(IndexAware<K,V> index,K key,V value,OpType op){
        switch (op) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }


    public static  <K,V> void  handlerEvent(IndexAware index, List<Map<String,String>> data, OpType op){
        switch (op) {
            case ADD:
                index.add(data);
                break;
            default:
                break;
        }
    }
}
