package ru.naumen.naumen_schooldairy.security.service;

import org.springframework.http.ResponseEntity;
import ru.naumen.naumen_schooldairy.security.dto.requests.*;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterResponse;

/**
 * Интерфейс для сервиса аутентификации
 */
public interface AuthenticationService {

    /**
     * Регистрирует нового пользователя.
     *
     * @param registerRequest запрос на регистрацию, содержащий необходимые данные.
     * @return ResponseEntity с результатом регистрации, включая сообщение об успехе или ошибке.
     */
    ResponseEntity<RegisterResponse> registerUser(RegisterRequest registerRequest);

    /**
     * Верифицирует регистрацию пользователя на основе предоставленного OTP.
     *
     * @param registerVerifyRequest запрос на верификацию, содержащий адрес электронной почты и OTP.
     * @return ResponseEntity с результатом верификации, включая сообщение об успехе или ошибке.
     */
    ResponseEntity<?> verifyUserRegistration(RegisterVerifyRequest registerVerifyRequest);

    /**
     * Аутентифицирует пользователя на основе введенных учетных данных.
     *
     * @param loginRequest запрос на вход, содержащий адрес электронной почты и пароль.
     * @return ResponseEntity с результатом аутентификации, включая JWT-токен при успешной аутентификации.
     */
    ResponseEntity<?> loginUser(LoginRequest loginRequest);

    /**
     * Повторно отправляет OTP на адрес электронной почты пользователя для восстановления доступа.
     *
     * @param forgotPasswordRequest запрос на восстановление пароля, содержащий адрес электронной почты.
     * @return ResponseEntity с результатом операции отправки OTP.
     */
    ResponseEntity<?> resendOtp(ForgotPasswordRequest forgotPasswordRequest);

    /**
     * Верифицирует одноразовый пароль (OTP) для пользователя.
     *
     * @param registerVerifyRequest запрос на верификацию, содержащий адрес электронной почты и OTP.
     * @return ResponseEntity с результатом верификации OTP.
     */
    ResponseEntity<?> verifyOtp(RegisterVerifyRequest registerVerifyRequest);

    /**
     * Сбрасывает пароль пользователя на основе нового пароля и подтверждения пароля.
     *
     * @param resetPasswordRequest запрос на сброс пароля, содержащий адрес электронной почты и новый пароль.
     * @return ResponseEntity с результатом операции сброса пароля.
     */
    ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest);

    /**
     * Возвращает профиль пользователя по его адресу электронной почты.
     *
     * @param forgotPasswordRequest запрос, содержащий адрес электронной почты пользователя.
     * @return ResponseEntity с профилем пользователя или сообщением об ошибке.
     */
    ResponseEntity<?> myProfile(ForgotPasswordRequest forgotPasswordRequest);
}
