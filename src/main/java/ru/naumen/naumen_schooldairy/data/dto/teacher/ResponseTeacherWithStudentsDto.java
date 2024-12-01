package ru.naumen.naumen_schooldairy.data.dto.teacher;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithStudentsDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией об учителе и его учениках.
 * Этот класс содержит идентификатор учителя, его имя, фамилию, отчество,
 * номер телефона и набор классов с учениками.
 *
 * @param id            идентификатор учителя
 * @param name          имя учителя
 * @param surname       фамилия учителя
 * @param patronymic    отчество учителя
 * @param phoneNumber   номер телефона учителя
 * @param schoolClasses набор классов с учениками, относящимися к данному учителю
 */
public record ResponseTeacherWithStudentsDto(Long id, String name, String surname, String patronymic,
                                             String phoneNumber,
                                             Set<ResponseSchoolClassWithStudentsDto> schoolClasses)
        implements Serializable {
}