/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.mobaas.stfx.NavItemStyleTag;
import com.mobaas.stfx.NavSubItemStyleTag;
import com.mobaas.stfx.shiro.ShiroTagsEx;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class FreemarkerConfig {

	@Autowired
	private freemarker.template.Configuration configuration;
	
	@PostConstruct
	public void setSharedVariable(){
		configuration.setSharedVariable("shiro", new ShiroTagsEx());//标签名与标签类
		configuration.setSharedVariable("navItemStyle", new NavItemStyleTag());
		configuration.setSharedVariable("navSubItemStyle", new NavSubItemStyleTag());
		configuration.setNumberFormat("#");
		configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");  
		configuration.setDateFormat("yyyy-MM-dd");  
		configuration.setTimeFormat("HH:mm:ss");
	}
}
