package cn.dazhiyy.ad.listener;

import cn.dazhiyy.ad.index.OpType;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className IListener
 * @description 监听器 用于监听binlog事件
 * @date 2019/3/23 17:39
 */
public interface IListener {

    /**
     * 注册监听
     */
    void registered();

    /**
     * 处理事件
     * @param binlogData binlog传递过来的数据
     * @param op 操作处理
     */
    void onEvent(BinlogData binlogData, OpType op);

}
