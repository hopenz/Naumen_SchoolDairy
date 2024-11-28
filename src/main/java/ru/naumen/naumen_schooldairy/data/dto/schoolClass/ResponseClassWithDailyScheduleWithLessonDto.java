package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleWithLessonDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.SchoolClass}
 */
public record ResponseClassWithDailyScheduleWithLessonDto(Long id, String className,
                                                          Set<ResponseDailyScheduleWithLessonDto> dailySchedules)
        implements Serializable {
}