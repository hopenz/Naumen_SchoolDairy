package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithScheduleDto;

import java.io.Serializable;

/**
 * DTO для представления информации о студенте с расписанием.
 *
 * @param id                                 уникальный идентификатор студента.
 * @param name                               имя студента.
 * @param surname                            фамилия студента.
 * @param patronymic                         отчество студента.
 * @param responseSchoolClassWithScheduleDto объект ResponseSchoolClassWithScheduleDto,
 *                                           представляющий класс студента и его расписание.
 */
public record ResponseStudentWithScheduleDto(Long id, String name, String surname, String patronymic,
                                             ResponseSchoolClassWithScheduleDto responseSchoolClassWithScheduleDto)
        implements Serializable {
}
