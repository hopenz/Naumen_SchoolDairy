package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonWithSubjectDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.DailySchedule}
 */
public record ResponseDailyScheduleWithLessonDto(Long id, LocalDate dateDay,
                                                 Set<ResponseLessonWithSubjectDto> lessons) implements Serializable {
}