package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;

/**
 * Класс, представляющий профиль пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    /**
     * Уникальный идентификатор пользователя.
     * Используется для однозначной идентификации пользователя в системе.
     */
    private Long id;

    /**
     * Адрес электронной почты пользователя.
     * Используется для аутентификации и связи с пользователем.
     */
    private String email;

    /**
     * Роль пользователя в системе.
     * Определяет права доступа и функциональность, доступные пользователю.
     */
    private Role role;

    /**
     * Статус официальной активации пользователя.
     * True, если пользователь официально активирован; иначе false.
     */
    private Boolean isOfficiallyEnabled;
}
