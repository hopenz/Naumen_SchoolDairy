package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import java.io.Serializable;

/**
 * DTO для представления информации о школьном классе.
 *
 * @param id        уникальный идентификатор школьного класса.
 * @param className название школьного класса.
 */
public record ResponseSchoolClassDto(Long id, String className) implements Serializable {
}
