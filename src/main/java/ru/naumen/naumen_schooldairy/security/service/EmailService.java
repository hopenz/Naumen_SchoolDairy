package ru.naumen.naumen_schooldairy.security.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

/**
 * Сервис для отправки электронных писем
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    /**
     * Объект для отправки электронных писем
     */
    private final JavaMailSender javaMailSender;

    /**
     * Адрес электронной почты отправителя
     */
    @Value("${spring.mail.username}")
    private String emailAddress;

    /**
     * Отправляет электронное письмо с OTP, повторяя попытки в случае ошибок.
     *
     * @param to  адрес электронной почты получателя.
     * @param otp одноразовый пароль для отправки.
     * @return CompletableFuture с результатом операции отправки.
     * @throws MessagingException           если возникает ошибка при отправке сообщения.
     * @throws UnsupportedEncodingException если кодировка не поддерживается.
     */
    @Async
    @Retryable(
            retryFor = MessagingException.class,
            maxAttempts = 4,
            backoff = @Backoff(delay = 3000)
    )
    public CompletableFuture<Integer> sendEmailWithRetry(String to, String otp) throws MessagingException, UnsupportedEncodingException {
        try {
            sendOtpByEmail(to, otp);
            return CompletableFuture.completedFuture(1);
        } catch (MessagingException e) {
            return CompletableFuture.completedFuture(handleMessagingException(e));
        } catch (UnsupportedEncodingException e) {
            return CompletableFuture.completedFuture(handleUnsupportedEncodingException(e));
        }
    }

    /**
     * Обрабатывает исключение MessagingException, возникающее при отправке электронного письма.
     *
     * @param e исключение, которое нужно обработать.
     * @return -1 в случае ошибки.
     */
    @Recover
    public int handleMessagingException(MessagingException e) {
        log.error("Maximum attempt reached, failed to send email");
        log.error("Error message: {}", e.getMessage());
        return -1;
    }

    /**
     * Обрабатывает исключение UnsupportedEncodingException, возникающее при отправке электронного письма.
     *
     * @param e исключение, которое нужно обработать.
     * @return -1 в случае ошибки.
     */
    @Recover
    public int handleUnsupportedEncodingException(UnsupportedEncodingException e) {
        log.error("Maximum attempt reached , failed to send email");
        log.error("Error message : {}", e.getMessage());
        return -1;
    }

    /**
     * Отправляет одноразовый пароль (OTP) по электронной почте.
     *
     * @param to  адрес электронной почты получателя.
     * @param otp одноразовый пароль для отправки.
     * @throws MessagingException           если возникает ошибка при создании или отправке сообщения.
     * @throws UnsupportedEncodingException если кодировка не поддерживается.
     */
    public void sendOtpByEmail(String to, String otp) throws MessagingException, UnsupportedEncodingException {
        log.info("Trying to send email to {}", to);

        String senderName = "Школьный дневник";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(emailAddress, senderName);
        helper.setTo(to);
        helper.setSubject("Одноразовый код для подтверждения электронной почты");
        String htmlContent = "<html>"
                + "<body>"
                + "<p>Дорогой пользователь,</p>"
                + "<p>Одноразовый код для подтверждения вашего адреса электронной почты: "
                + "<strong style='font-size:18px; color:blue;'>" + otp + "</strong>.</p>"
                + "<p>Одноразовый код действителен <strong>10 минут</strong>.</p>"
                + "<p style='color:gray; font-size:12px;'>(Это сообщение сгенерировано автоматически, "
                + "так что отвечать на него не нужно)</p>"
                + "<p>С уважением,<br/>Команда: Овчинников и Петрушина</p>"
                + "</body>"
                + "</html>";
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
        log.info("Email has been sent successfully to {}", to);
    }
}
