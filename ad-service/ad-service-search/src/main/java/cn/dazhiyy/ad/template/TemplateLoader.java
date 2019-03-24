package cn.dazhiyy.ad.template;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql.template
 * @className TemplateParser
 * @description TODO
 * @date 2019/3/23 01:06
 */
public interface TemplateLoader<R> {

    /**
     * 加载资源文件并，返回对应的bean对象
     *
     * @param resourcePath 资源路径
     * @return bean对象
     */
    R loader(String resourcePath);

    /**
     * 获得资源 (若没有加载，则返回null)
     *
     * @return
     */
    R getResource();
}
