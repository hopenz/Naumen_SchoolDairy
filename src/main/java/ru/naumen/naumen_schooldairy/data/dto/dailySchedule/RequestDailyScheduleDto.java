package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.RequestLessonDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.DailySchedule}
 */
public record RequestDailyScheduleDto(LocalDate dateDay, String dayOfWeek,
                                      List<RequestLessonDto> lessons) implements Serializable {
}