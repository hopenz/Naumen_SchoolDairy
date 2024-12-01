package ru.naumen.naumen_schooldairy.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

/**
 * Конфигурационный класс для настройки отправки электронной почты
 */
@Configuration
@EnableRetry
@EnableAsync
public class MailConfiguration {

    /**
     * Имя пользователя для аутентификации на SMTP-сервере.
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Пароль для аутентификации на SMTP-сервере.
     */
    @Value("${spring.mail.password}")
    private String password;

    /**
     * Хост SMTP-сервера для отправки электронной почты.
     */
    @Value("${spring.mail.host}")
    private String host;

    /**
     * Порт SMTP-сервера для отправки электронной почты.
     */
    @Value("${spring.mail.port}")
    private int port;

    /**
     * Создает бин JavaMailSender, настроенный для отправки электронной почты.
     *
     * @return экземпляр JavaMailSender, который можно использовать для отправки писем.
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
