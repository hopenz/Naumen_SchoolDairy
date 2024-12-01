package ru.naumen.naumen_schooldairy.security.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий запрос на восстановление пароля
 */
@Data
@NoArgsConstructor
public class ForgotPasswordRequest {

    /**
     * Адрес электронной почты пользователя, который запрашивает восстановление пароля.
     * Должен быть действительным адресом электронной почты и не может быть пустым.
     */
    @Email(message = "enter a valid email")
    @NotBlank(message = "Email can't be blank")
    private String email;
}
