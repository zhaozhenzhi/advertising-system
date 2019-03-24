package cn.dazhiyy.ad.client;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.client
 * @className BinlogClient
 * @description TODO
 * @date 2019/3/23 17:44
 */
public interface BinlogClient {

    /**
     * 连接配置的所有的binlog
     */
    void connect();

    /**
     * 指定开启binlog连接
     * @param name
     */
    void connect(String name);

    /**
     * 关闭所有的binlog连接
     */
    void close();

    /**
     * 指定关闭连接
     * @param name
     */
    void close(String name);

}
