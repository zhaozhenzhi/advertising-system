import cn.dazhiyy.ad.common.factory.BeanInstanceFactory;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName PACKAGE_NAME
 * @className MysqlBinlog
 * @description TODO
 * @date 2019/3/20 22:48
 */
public class MysqlBinlog {

    public static void main(String[] args) {
        MysqlBinlog mysqlBinlog = BeanInstanceFactory.newInstance(MysqlBinlog::new);
    }
}
