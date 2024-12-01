package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;

/**
 * Класс, представляющий ответ на запрос обновления токена
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponse {

    /**
     * Новый токен доступа, выданный пользователю после обновления.
     * Используется для аутентификации пользователя в последующих запросах.
     */
    private String accessToken;

    /**
     * Адрес электронной почты пользователя, которому принадлежит токен.
     * Используется для идентификации пользователя.
     */
    private String email;

    /**
     * Роль пользователя, связанная с токеном доступа.
     * Используется для определения прав доступа пользователя в приложении.
     */
    private Role role;
}
