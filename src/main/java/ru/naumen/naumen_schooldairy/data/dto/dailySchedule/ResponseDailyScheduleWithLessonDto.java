package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonWithSubjectDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO для ответа с информацией о ежедневном расписании,
 * включая уроки и соответствующие предметы.
 * Этот класс представляет собой запись, содержащую информацию о дате,
 * идентификаторе расписания и наборе уроков с предметами.
 *
 * @param id      идентификатор расписания
 * @param dateDay дата, представляющая день расписания
 * @param lessons набор уроков, запланированных на этот день, с информацией о предметах
 */
public record ResponseDailyScheduleWithLessonDto(Long id, LocalDate dateDay,
                                                 Set<ResponseLessonWithSubjectDto> lessons) implements Serializable {
}