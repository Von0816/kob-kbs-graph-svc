package com.kobkbs.graphsvc.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisCacheConfig {

  @Bean 
  public RedisCacheConfiguration redisCacheConfiguration() {
    
    return RedisCacheConfiguration.defaultCacheConfig()
      .entryTtl(Duration.ofMinutes(60))
      .disableCachingNullValues()
      .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    final RedisCacheConfiguration defCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10));

    return (builder) -> builder
      .withCacheConfiguration("searchResult", defCacheConfig)
      .withCacheConfiguration("eventCache", defCacheConfig)
      .withCacheConfiguration("personCache", defCacheConfig)
      .withCacheConfiguration("hmoCache", defCacheConfig)
      .withCacheConfiguration("rightCache", defCacheConfig)
      .withCacheConfiguration("tsCache", defCacheConfig)
      .withCacheConfiguration("placeCache", defCacheConfig)
      .withCacheConfiguration("groupCache", defCacheConfig);
  }
}
