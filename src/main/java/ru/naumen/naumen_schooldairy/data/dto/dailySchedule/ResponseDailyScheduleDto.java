package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonDto;

import java.io.Serializable;
import java.util.Set;

public record ResponseDailyScheduleDto(Long id, Set<ResponseLessonDto> responseLessonDto,
                                       String dayOfWeek) implements Serializable {
}
