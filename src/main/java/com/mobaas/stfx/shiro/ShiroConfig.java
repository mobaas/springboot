/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mobaas.stfx.service.SysUserService;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

/**
 * @author Billy Zhang
 */
@Configuration
public class ShiroConfig {
	
	@Autowired
	private net.sf.ehcache.CacheManager cacheManager;
	
	@Autowired
	private SysUserService userService;
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

		RememberMeFilter rmFilter = new RememberMeFilter();
		rmFilter.setUserService(userService);
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("rememberMe", rmFilter);
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		filterChainDefinitionMap.put("/manage/login", "anon");

		filterChainDefinitionMap.put("/manage/**", "rememberMe, user");
		
		shiroFilterFactoryBean.setLoginUrl("/manage/login");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(authorizingRealm()); // 将Realm注入到SecurityManager中。
		securityManager.setCacheManager(ehCacheManager()); // 注入缓存对象。
		securityManager.setRememberMeManager(cookieRememberMeManager()); // 注入rememberMeManager;
		return securityManager;
	}

	@Bean
	public AuthorizingRealm authorizingRealm() {
		AuthorizingRealmImpl realm = new AuthorizingRealmImpl();
		realm.setCredentialsMatcher(hashedCredentialsMatcher()); // 设置解密规则
		return realm;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hcMatcher = new HashedCredentialsMatcher();
		hcMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hcMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hcMatcher;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {

		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");

		// <!-- 记住我cookie生效时间3天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	// cookie管理对象;
	@Bean
	public CookieRememberMeManager cookieRememberMeManager() {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		manager.setCookie(rememberMeCookie());
		return manager;
	}
}
