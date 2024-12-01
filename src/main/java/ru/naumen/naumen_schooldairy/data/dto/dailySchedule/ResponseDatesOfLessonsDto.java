package ru.naumen.naumen_schooldairy.data.dto.dailySchedule;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO для ответа с датами уроков.
 * Этот класс представляет собой запись, содержащую информацию о дате урока
 * и соответствующем идентификаторе.
 *
 * @param id      идентификатор урока
 * @param dateDay дата, представляющая день, когда проходит урок
 */
public record ResponseDatesOfLessonsDto(Long id, LocalDate dateDay) implements Serializable {
}