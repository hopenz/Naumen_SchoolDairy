package ru.naumen.naumen_schooldairy.security.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
      public CacheManager cacheManager() {
          CaffeineCacheManager cacheManager = new CaffeineCacheManager("user");
          cacheManager.setCaffeine(caffeineCacheBuilder());
          return cacheManager;
      }

    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
          return Caffeine.newBuilder()
                  .initialCapacity(10)
                  .maximumSize(500)
                  .expireAfterWrite(2, TimeUnit.MINUTES)
                  .recordStats();
    }




}
