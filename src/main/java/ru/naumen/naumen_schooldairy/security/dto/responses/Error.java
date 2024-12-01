package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий информацию об ошибке
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Error {

    /**
     * Код ошибки, который может быть использован для идентификации типа ошибки.
     */
    private String errorCode;

    /**
     * Сообщение об ошибке, которое предоставляет описание проблемы.
     */
    private String errorMessage;

    /**
     * Дополнительная информация о возникшей ошибке.
     * Может содержать контекст или рекомендации по устранению проблемы.
     */
    private String additionalInfo;
}
