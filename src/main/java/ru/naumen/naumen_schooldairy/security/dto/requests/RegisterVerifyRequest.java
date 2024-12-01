package ru.naumen.naumen_schooldairy.security.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий запрос на верификацию регистрации пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterVerifyRequest {

    /**
     * Адрес электронной почты пользователя, который требуется для верификации.
     * Должен быть действительным адресом электронной почты и не может быть пустым.
     */
    @NotBlank(message = "Email can't be blank")
    @Email(message = "enter a valid email")
    private String email;

    /**
     * Одноразовый пароль (OTP), используемый для верификации регистрации.
     * Не может быть пустым и должен содержать ровно 6 символов.
     */
    @NotBlank(message = "OTP can't be blank")
    @Size(min = 6, max = 6, message = "OTP must be 6 characters long")
    private String otp;
}
