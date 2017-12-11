package com.galudisu.handsonic.config;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.List;

@Configuration
@EnableCaching
@EnableRedisHttpSession
@AutoConfigureAfter(Config.class)
public class CacheConfig {
    @Primary
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setUsePrefix(true);

        List<CacheManager> lists = Lists.newArrayList();
        lists.add(redisCacheManager);

        CompositeCacheManager cacheManager = new CompositeCacheManager();
        cacheManager.setCacheManagers(lists);
        cacheManager.setFallbackToNoOpCache(true);
        return cacheManager;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}

