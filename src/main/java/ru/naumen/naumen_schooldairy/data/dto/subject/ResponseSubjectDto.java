package ru.naumen.naumen_schooldairy.data.dto.subject;

import java.io.Serializable;

/**
 * DTO для представления информации о предмете.
 *
 * @param id   уникальный идентификатор предмета.
 * @param name название предмета.
 */
public record ResponseSubjectDto(Long id, String name) implements Serializable {
}
