package ru.naumen.naumen_schooldairy.data.dto.mark;

import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO для представления информации об оценке.
 *
 * @param id                 уникальный идентификатор оценки.
 * @param mark               значение оценки.
 * @param gradeDate          дата, когда была выставлена оценка.
 * @param responseSubjectDto объект ResponseSubjectDto, представляющий предмет, к которому относится оценка.
 */
public record ResponseMarkDto(Long id, Integer mark, LocalDate gradeDate,
                              ResponseSubjectDto responseSubjectDto) implements Serializable {

}
