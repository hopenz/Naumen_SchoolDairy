package ru.naumen.naumen_schooldairy.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import ru.naumen.naumen_schooldairy.security.jwt.JwtAuthenticationFilter;

/**
 * Конфигурационный класс для настройки безопасности приложения
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * Обработчик для неавторизованных запросов
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Фильтр для обработки JWT
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Провайдер аутентификации
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * Создает бин для построения MvcRequestMatcher.
     *
     * @param introspector объект HandlerMappingIntrospector для анализа маршрутов.
     * @return экземпляр MvcRequestMatcher. Builder для настройки маршрутов.
     */
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/file/*",
            "/error/*",
            "/"
    };

    /**
     * Настраивает цепочку фильтров безопасности.
     *
     * @param http объект HttpSecurity для настройки безопасности HTTP.
     * @param mvc  объект MvcRequestMatcher. Builder для настройки маршрутов.
     * @return настроенная цепочка фильтров безопасности.
     * @throws Exception если возникает ошибка при настройке безопасности.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.pattern("/api/auth/**")).permitAll()
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers("/api/schedule/**", "/api/subject/**", "/api/marks/**",
                                "/api/homeworks/**", "/api/teacher/**").hasRole("TEACHER")
                        .requestMatchers("/api/students/**").hasAnyRole("STUDENT", "TEACHER")
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
