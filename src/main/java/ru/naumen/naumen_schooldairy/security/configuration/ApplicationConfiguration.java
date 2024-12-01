package ru.naumen.naumen_schooldairy.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.naumen.naumen_schooldairy.exception.ResourceNotFoundException;
import ru.naumen.naumen_schooldairy.security.repository.UserRepository;

/**
 * Конфигурационный класс приложения, отвечающий за настройку компонентов безопасности
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Создает бин PasswordEncoder, используемый для шифрования паролей.
     *
     * @return экземпляр BCryptPasswordEncoder для шифрования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    ;

    /**
     * Создает бин UserDetailsService, который загружает пользователя по его имени (email).
     *
     * @return реализация UserDetailsService, которая ищет пользователя в репозитории по email.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Создает бин AuthenticationManager, используемый для управления аутентификацией.
     *
     * @param config конфигурация аутентификации.
     * @return экземпляр AuthenticationManager для обработки аутентификации пользователей.
     * @throws Exception если возникает ошибка при получении менеджера аутентификации.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Создает бин AuthenticationProvider, который использует DaoAuthenticationProvider
     * для аутентификации пользователей с использованием UserDetailsService и PasswordEncoder.
     *
     * @return экземпляр DaoAuthenticationProvider для аутентификации пользователей.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

