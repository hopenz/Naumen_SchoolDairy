package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;

import java.io.Serializable;
import java.time.LocalDate;

public record ResponseStudentDto(Long id, String name, String surname, String patronymic,
                                 LocalDate dateOfBirth, String parentContact, String phoneNumber,
                                 ResponseSchoolClassDto responseSchoolClassDto) implements Serializable {
}
