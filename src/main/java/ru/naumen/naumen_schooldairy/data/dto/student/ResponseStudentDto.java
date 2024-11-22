package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO для представления информации о студенте.
 *
 * @param id                     уникальный идентификатор студента.
 * @param name                   имя студента.
 * @param surname                фамилия студента.
 * @param patronymic             отчество студента.
 * @param dateOfBirth            дата рождения студента.
 * @param parentContact          контактная информация родителя.
 * @param phoneNumber            номер телефона студента.
 * @param responseSchoolClassDto объект ResponseSchoolClassDto, представляющий класс, к которому принадлежит студент.
 */
public record ResponseStudentDto(Long id, String name, String surname, String patronymic,
                                 LocalDate dateOfBirth, String parentContact, String phoneNumber,
                                 ResponseSchoolClassDto responseSchoolClassDto) implements Serializable {
}
