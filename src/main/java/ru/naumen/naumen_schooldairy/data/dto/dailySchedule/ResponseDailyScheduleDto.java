package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO для представления расписания на день.
 * Содержит информацию о занятиях, связанных с конкретным днем недели.
 *
 * @param id                уникальный идентификатор расписания
 * @param responseLessonDto набор объектов ResponseLessonDto, представляющих занятия в этот день
 * @param dayOfWeek         день недели, к которому относится расписание
 */
public record ResponseDailyScheduleDto(Long id, Set<ResponseLessonDto> responseLessonDto,
                                       String dayOfWeek) implements Serializable {
}
