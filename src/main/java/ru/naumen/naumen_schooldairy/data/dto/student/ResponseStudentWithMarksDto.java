package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией о студенте и его оценках.
 * Этот класс содержит идентификатор студента, его имя, фамилию, отчество
 * и набор оценок, полученных студентом.
 *
 * @param id         идентификатор студента
 * @param name       имя студента
 * @param surname    фамилия студента
 * @param patronymic отчество студента
 * @param marks      набор оценок, полученных студентом
 */
public record ResponseStudentWithMarksDto(Long id, String name, String surname, String patronymic,
                                          Set<ResponseMarkDto> marks) implements Serializable {
}