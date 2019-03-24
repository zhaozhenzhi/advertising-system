package cn.dazhiyy.ad.template;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className TemplatePaser
 * @description TODO
 * @date 2019/3/23 03:06
 */
public interface TemplateParse<R,T> {

    /**
     * T -> target 目标  R -> resource 资源 资源转换目标的抽象
     *
     * @param resource 待转换的资源
     * @return
     */
    T parse(R resource);
}
