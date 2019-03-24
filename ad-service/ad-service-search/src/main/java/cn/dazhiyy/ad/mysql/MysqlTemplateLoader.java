package cn.dazhiyy.ad.mysql;

import cn.dazhiyy.ad.mysql.template.TemplateJson;
import cn.dazhiyy.ad.template.TemplateLoader;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql.template
 * @className TemplateParser
 * @description 解析资源文件
 * @date 2019/3/23 00:59
 */
@Slf4j
public class MysqlTemplateLoader implements TemplateLoader<TemplateJson> {

    private TemplateJson templateJson;

    /**
     * 解析资源文件 并返回json
     *
     * @param resourcePath
     * @return
     */
    @Override
    public TemplateJson loader(String resourcePath) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = loader.getResourceAsStream(resourcePath);
        try {
            this.templateJson = JSON.parseObject(resourceAsStream, Charset.defaultCharset(), TemplateJson.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return this.templateJson;
    }

    @Override
    public TemplateJson getResource() {
        return templateJson;
    }
}
