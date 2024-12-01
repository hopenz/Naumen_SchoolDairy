package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий ответ на запрос регистрации пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    /**
     * Сообщение, содержащее информацию о результате выполнения запроса на регистрацию.
     * Может использоваться для передачи как успешных, так и ошибочных сообщений.
     */
    private String message;
}
