package ru.naumen.naumen_schooldairy.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурационный класс для настройки Spring MVC
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * Настраивает параметры CORS для приложения.
     *
     * @param registry объект CorsRegistry, используемый для настройки CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
                .allowCredentials(true)
                .maxAge(1728000);
    }
}
