package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.RequestLessonDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO для запроса ежедневного расписания.
 * Этот класс представляет собой запись, содержащую информацию о дате,
 * дне недели и списке уроков для определенного дня.
 *
 * @param dateDay   дата, представляющая день расписания
 * @param dayOfWeek название дня недели
 * @param lessons   список уроков, запланированных на этот день
 */
public record RequestDailyScheduleDto(LocalDate dateDay, String dayOfWeek,
                                      List<RequestLessonDto> lessons) implements Serializable {
}