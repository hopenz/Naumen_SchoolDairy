package ru.naumen.naumen_schooldairy.data.dto.teacher;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseClassWithDailyScheduleWithLessonDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Teacher}
 */
public record ResponseTeacherWithDateOfLessonsDto(Long id, String name, String surname, String patronymic,
                                                  Set<ResponseClassWithDailyScheduleWithLessonDto> schoolClasses)
        implements Serializable {
}