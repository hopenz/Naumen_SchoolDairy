package ru.naumen.naumen_schooldairy.security.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;

/**
 * Класс, представляющий запрос на регистрацию пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    /**
     * Имя пользователя.
     * Не может быть пустым.
     */
    @NotBlank(message = "First name can't be blank")
    private String firstName;

    /**
     * Фамилия пользователя.
     * Не может быть пустой.
     */
    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    /**
     * Адрес электронной почты пользователя.
     * Должен быть действительным адресом электронной почты и не может быть пустым.
     */
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email entered")
    private String email;

    /**
     * Пароль пользователя.
     * Не может быть пустым и должен соответствовать заданным критериям безопасности:
     * минимум 8 символов, включая одну заглавную букву, одну строчную букву и одну цифру.
     */
    @NotBlank(message = "Password can't be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least 8 characters, one uppercase, one lowercase and one number")
    private String password;

    /**
     * Номер телефона пользователя.
     * Должен соответствовать формату: +(код)XXXXXXXXXX.
     */
    @Pattern(regexp = "^\\+\\d{1,3}\\d{10}$", message = "Invalid phone number, please enter in the format +(code)XXXXXXXXXX")
    private String phoneNumber;

    /**
     * Роль пользователя.
     * Не может быть null; необходимо выбрать роль при регистрации.
     */
    @NotNull(message = "Please choose a role")
    private Role role;
}
