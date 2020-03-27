/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * EhCache 配置类
 * @author billy  (billy_zh@126.com)
 *
 */
@Configuration
public class EhCacheConfig extends CachingConfigurerSupport {

	@Override
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}

	@Bean
    public EhCacheCacheManager cacheCacheManager(net.sf.ehcache.CacheManager cacheManager){
        return new EhCacheCacheManager(cacheManager);
    }

	@Bean
	public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation( new ClassPathResource("ehcache.xml") );
        return ehCacheManagerFactoryBean;
    }
	
}
