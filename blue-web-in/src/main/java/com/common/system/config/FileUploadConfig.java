package com.common.system.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {
	/**
	 * 文件上传临时路径
	 */
	 @Bean
	 MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setLocation("C:/soft/tomcat/webapps/pics/");
	    return factory.createMultipartConfig();
	}
}
