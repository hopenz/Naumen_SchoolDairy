package ru.naumen.naumen_schooldairy.security.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий запрос на вход в систему
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    /**
     * Адрес электронной почты пользователя, используемый для входа в систему.
     * Должен быть действительным адресом электронной почты и не может быть пустым.
     */
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email can't be blank")
    private String email;

    /**
     * Пароль пользователя, используемый для входа в систему.
     * Не может быть пустым.
     */
    @NotBlank(message = "Password can't be blank")
    private String password;
}
