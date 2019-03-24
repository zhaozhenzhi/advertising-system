package cn.dazhiyy.ad.sender;

import cn.dazhiyy.ad.index.OpType;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.sender
 * @className ISender
 * @description TODO
 * @date 2019/3/24 16:46
 */
public interface ISender<T> {

    /**
     * 投递数据
     *
     * @param data 数据
     * @param op 操作
     */
    void onSender(T data, OpType op);
}
