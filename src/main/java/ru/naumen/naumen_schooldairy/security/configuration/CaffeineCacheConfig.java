package ru.naumen.naumen_schooldairy.security.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Конфигурационный класс для настройки кэширования с использованием Caffeine
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    /**
     * Создает бин CacheManager, который управляет кэшем пользователей.
     *
     * @return экземпляр CaffeineCacheManager, настроенный для кэширования данных пользователей.
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("user");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    /**
     * Создает бин Caffeine, который настраивает параметры кэша.
     *
     * @return экземпляр Caffeine, настроенный с параметрами кэширования,
     * такими как начальный размер, максимальный размер и время жизни записей в кэше.
     */
    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(500)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .recordStats();
    }
}
