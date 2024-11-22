package ru.naumen.naumen_schooldairy.data.dto.lesson;

import ru.naumen.naumen_schooldairy.data.dto.homework.ResponseHomeworkDto;
import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO для представления информации о занятии.
 *
 * @param id                  уникальный идентификатор занятия.
 * @param responseMarkDto     набор объектов ResponseMarkDto, представляющих оценки, связанные с занятием.
 * @param responseSubjectDto  объект ResponseSubjectDto, представляющий предмет занятия.
 * @param responseHomeworkDto набор объектов ResponseHomeworkDto, представляющих домашние задания, связанные с занятием.
 * @param lessonNumber        номер занятия в расписании.
 */
public record ResponseLessonDto(Long id, Set<ResponseMarkDto> responseMarkDto,
                                ResponseSubjectDto responseSubjectDto,
                                Set<ResponseHomeworkDto> responseHomeworkDto,
                                Integer lessonNumber) implements Serializable {
}
