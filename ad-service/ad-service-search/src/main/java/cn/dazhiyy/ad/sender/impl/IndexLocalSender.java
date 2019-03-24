package cn.dazhiyy.ad.sender.impl;


import cn.dazhiyy.ad.config.TableTempalteConfig;
import cn.dazhiyy.ad.index.IndexCacheProvider;
import cn.dazhiyy.ad.index.OpType;
import cn.dazhiyy.ad.listener.BinlogData;
import cn.dazhiyy.ad.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.sender.impl
 * @className IndexSender
 * @description TODO
 * @date 2019/3/24 16:47
 */
@Component
public class IndexLocalSender implements ISender<BinlogData> {

    @Autowired
    private IndexCacheProvider indexCacheProvider;


    @Override
    public void onSender(BinlogData data, OpType op) {
        TableTempalteConfig tempalteConfig = data.getTempalteConfig();
        indexCacheProvider.op(tempalteConfig.getTableName(),op, data.getAfter());
    }
}
