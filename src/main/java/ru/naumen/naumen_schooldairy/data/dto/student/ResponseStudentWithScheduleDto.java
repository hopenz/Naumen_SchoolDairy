package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithScheduleDto;

import java.io.Serializable;

public record ResponseStudentWithScheduleDto(Long id, String name, String surname, String patronymic,
                                             ResponseSchoolClassWithScheduleDto responseSchoolClassWithScheduleDto)
        implements Serializable {
}
