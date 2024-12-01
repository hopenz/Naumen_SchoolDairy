package ru.naumen.naumen_schooldairy.data.dto.teacher;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseClassWithDailyScheduleWithLessonDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией об учителе и его расписании уроков.
 * Этот класс содержит идентификатор учителя, его имя, фамилию, отчество
 * и набор классов с ежедневными расписаниями уроков.
 *
 * @param id            идентификатор учителя
 * @param name          имя учителя
 * @param surname       фамилия учителя
 * @param patronymic    отчество учителя
 * @param schoolClasses набор классов с ежедневными расписаниями уроков, связанных с учителем
 */
public record ResponseTeacherWithDateOfLessonsDto(Long id, String name, String surname, String patronymic,
                                                  Set<ResponseClassWithDailyScheduleWithLessonDto> schoolClasses)
        implements Serializable {
}