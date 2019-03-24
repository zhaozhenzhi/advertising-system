package cn.dazhiyy.ad.index;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.handler
 * @className OpType
 * @description TODO
 * @date 2019/3/22 14:06
 */
public enum OpType {
    ADD,UPDATE,DELETE,OTHER;

    public static OpType to(EventType eventType) {

        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;

            default:
                return OTHER;
        }
    }
}
