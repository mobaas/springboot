/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mobaas.stfx.interceptor.UserInterceptor;

/**
 * @author: billy zhang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer { 

	@Bean
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
	
	 /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInterceptor())   
        			.addPathPatterns("/**")
					.excludePathPatterns("/", "/index", "/login")
					.excludePathPatterns("/error", "/error40x", "/error50x")
        			.excludePathPatterns("/manage/**")  // 管理后台
        			.excludePathPatterns("/favicon.ico")   // 静态资源
        			.excludePathPatterns("/dist/**")   // 静态资源
        			.excludePathPatterns("/plugins/**")   // 静态资源
        			.excludePathPatterns("/bower_components/**")   // 静态资源
        			.excludePathPatterns("/layer/**") // 静态资源
        			.excludePathPatterns("/webuploader/**") // 静态资源
				    .excludePathPatterns("/common/**") // 静态json文件
					.excludePathPatterns("/js/**") // 静态json文件
					.excludePathPatterns("/css/**") // 静态json文件
        			;
        
    }
}
