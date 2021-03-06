package com.common.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by Mr.Yangxiufeng on 2017/6/15.
 * Time:16:08
 * ProjectName:Common-admin
 */
@Configuration
public class FreemarkerCustomConfig {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Bean
    public freemarker.template.Configuration getFreemarkerConfiguration(){
        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        return configuration;
    }
}
