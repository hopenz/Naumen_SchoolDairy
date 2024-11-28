package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.DailySchedule}
 */
public record ResponseDatesOfLessonsDto(Long id, LocalDate dateDay) implements Serializable {
}