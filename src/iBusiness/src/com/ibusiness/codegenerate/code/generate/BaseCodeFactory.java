package com.ibusiness.codegenerate.code.generate;

import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;

/**
 * 基础代码生成工厂类
 * 
 * @author JiangBo
 * 
 */
public class BaseCodeFactory {
    /**
     * 根据指定的模板文件路径，创建一个模板
     * @return
     * @throws IOException
     */
    public Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(super.getClass(), "/code/template");
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
}
