package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий общий ответ API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralAPIResponse {

    /**
     * Сообщение, которое содержит информацию о результате выполнения запроса.
     * Может использоваться для передачи как успешных, так и ошибочных сообщений.
     */
    private String message;
}
