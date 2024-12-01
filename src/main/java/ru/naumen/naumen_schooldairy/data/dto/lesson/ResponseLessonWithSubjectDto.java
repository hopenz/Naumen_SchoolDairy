package ru.naumen.naumen_schooldairy.data.dto.lesson;

import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;

/**
 * Класс для ответа с информацией об уроке и соответствующем предмете.
 * Этот класс содержит идентификатор урока и информацию о предмете,
 * к которому относится данный урок.
 *
 * @param id      идентификатор урока
 * @param subject объект, представляющий информацию о предмете, связанном с уроком
 */
public record ResponseLessonWithSubjectDto(Long id, ResponseSubjectDto subject) implements Serializable {
}