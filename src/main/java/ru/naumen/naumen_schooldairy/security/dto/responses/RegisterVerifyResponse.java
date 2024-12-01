package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;

/**
 * Класс, представляющий ответ на запрос верификации регистрации пользователя
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVerifyResponse {

    /**
     * Токен доступа, выданный пользователю после успешной верификации.
     * Используется для аутентификации пользователя в последующих запросах.
     */
    private String accessToken;

    /**
     * Токен обновления, используемый для получения нового токена доступа.
     * Позволяет пользователю оставаться аутентифицированным без повторного ввода учетных данных.
     */
    private String refreshToken;

    /**
     * Адрес электронной почты пользователя, которому принадлежит токен.
     * Используется для идентификации пользователя.
     */
    private String email;

    /**
     * Роль пользователя, связанная с токенами доступа и обновления.
     * Используется для определения прав доступа пользователя в приложении.
     */
    private Role role;

    /**
     * Флаг, указывающий, прошел ли пользователь верификацию.
     * True, если пользователь успешно верифицирован; иначе false.
     */
    private boolean isVerified;
}
