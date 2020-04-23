package com.mobaas.stfx.cache;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.mobaas.stfx.cache.CacheKeyGenerator;

@Configuration
@EnableCaching
@Profile({"dev", "prod"})
public class RedisCacheConfig extends CachingConfigurerSupport {

	private static final Logger log = LoggerFactory.getLogger(RedisCacheConfig.class);
	
	 @Override
    public KeyGenerator keyGenerator() {
        return new CacheKeyGenerator();
    }
	 
	 @Bean  
    @Override  
    public CacheErrorHandler errorHandler() {  
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {  
            @Override  
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {  
            	log.warn("CacheGetError, key:" + key + ", Message:" + e.getMessage()); 
            }  
  
            @Override  
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {  
                log.warn("CachePutError, key:" + key + ", Message:" + e.getMessage()); 
            }  
  
            @Override  
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {  
            	log.warn("CacheEvictError, key:" + key + ", Message:" + e.getMessage()); 
            }  
  
            @Override  
            public void handleCacheClearError(RuntimeException e, Cache cache) {  
            		log.warn(e.getMessage()); 
            }  
        };  
        return cacheErrorHandler;  
    }  
	 
	@Bean(name = "redisTemplate")
	public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
 
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
 
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         redisTemplate.setKeySerializer(keySerializer());
         redisTemplate.setHashKeySerializer(keySerializer());
         redisTemplate.setValueSerializer(valueSerializer());
         redisTemplate.setHashValueSerializer(valueSerializer());
         return redisTemplate;
     }
	
     @Primary
     @Bean
     public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
         //缓存配置对象
    	 	RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
 
         redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L)) //设置缓存的默认超时时间：30分钟
        		 .disableCachingNullValues()             //如果是空值，不缓存
                  .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))         //设置key序列化器
                  .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer())));  //设置value序列化器
  
          return RedisCacheManager
                  .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                  .cacheDefaults(redisCacheConfiguration).build();
      }
     
     @Bean
    public RedisSerializer<String> keySerializer() {
         return new StringRedisSerializer();
     }
 
     private RedisSerializer<Object> valueSerializer() {
         return new GenericJackson2JsonRedisSerializer();
     }
 }

