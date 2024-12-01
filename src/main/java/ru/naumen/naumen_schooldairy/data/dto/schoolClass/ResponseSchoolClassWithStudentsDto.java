package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс для ответа с информацией о школьном классе и его учениках.
 * Этот класс содержит идентификатор класса, название класса и набор учеников,
 * относящихся к данному классу.
 *
 * @param id        идентификатор школьного класса
 * @param className название школьного класса
 * @param students  набор учеников, относящихся к данному классу
 */
public record ResponseSchoolClassWithStudentsDto(Long id, String className,
                                                 Set<ResponseStudentDto> students) implements Serializable {
}