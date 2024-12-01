package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleWithLessonDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией о классе и его ежедневном расписании с уроками.
 * Этот класс содержит идентификатор класса, название класса и набор ежедневных расписаний,
 * включая информацию об уроках.
 *
 * @param id             идентификатор класса
 * @param className      название класса
 * @param dailySchedules набор ежедневных расписаний с уроками, связанными с данным классом
 */
public record ResponseClassWithDailyScheduleWithLessonDto(Long id, String className,
                                                          Set<ResponseDailyScheduleWithLessonDto> dailySchedules)
        implements Serializable {
}