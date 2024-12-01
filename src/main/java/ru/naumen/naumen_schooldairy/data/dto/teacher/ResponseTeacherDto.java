package ru.naumen.naumen_schooldairy.data.dto.teacher;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией об учителе.
 * Этот класс содержит идентификатор учителя, его имя, фамилию, отчество,
 * номер телефона и набор классов, в которых он преподает.
 *
 * @param id            идентификатор учителя
 * @param name          имя учителя
 * @param surname       фамилия учителя
 * @param patronymic    отчество учителя
 * @param phoneNumber   номер телефона учителя
 * @param schoolClasses набор классов, в которых преподает учитель
 */
public record ResponseTeacherDto(Long id, String name, String surname, String patronymic, String phoneNumber,
                                 Set<ResponseSchoolClassDto> schoolClasses) implements Serializable {
}