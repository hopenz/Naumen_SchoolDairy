package ru.naumen.naumen_schooldairy.security.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.security.constants.ApplicationConstants;

import java.util.Random;

/**
 * Сервис для генерации одноразовых паролей
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {

    /**
     * Символы, используемые для генерации OTP
     */
    String otpChar = ApplicationConstants.OTP_CHARACTERS;

    /**
     * Длина OTP
     */
    Integer otpLength = ApplicationConstants.OTP_LENGTH;

    /**
     * Генерирует одноразовый пароль (OTP).
     *
     * @return сгенерированный одноразовый пароль.
     */
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otp.append(otpChar.charAt(random.nextInt(otpChar.length())));
        }
        log.info("Generated OTP: {}", otp);
        return otp.toString();
    }

    /**
     * Получает одноразовый пароль (OTP) для указанного адреса электронной почты.
     * Этот метод также кэширует сгенерированный OTP.
     *
     * @param email адрес электронной почты, для которого генерируется OTP.
     * @return сгенерированный одноразовый пароль.
     */
    @CachePut(value = "user", key = "#email")
    public String getOtpForEmail(String email) {
        return generateOtp();
    }
}
