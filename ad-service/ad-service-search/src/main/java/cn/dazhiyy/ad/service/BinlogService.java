package cn.dazhiyy.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.service
 * @className BinlogService
 * @description TODO
 * @date 2019/3/22 15:06
 */
public class BinlogService {


    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("cdb-ahs4c0jv.gz.tencentcdb.com",10050,"root","dazhi-1993aini");

        client.registerEventListener(event -> {
            EventType type = event.getHeader().getEventType();
            if (type == EventType.TABLE_MAP) {
                TableMapEventData data = event.getData();
                System.out.println(data);
            }

            EventData data = event.getData();
            System.out.println(type);

            if (data instanceof UpdateRowsEventData) {
                System.out.println("update ---------");
                System.out.println(data.toString());

            } else if (data instanceof WriteRowsEventData) {
                System.out.println("write");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("detele");
                System.out.println(data.toString());
            }
        });
        client.connect();
    }
}
