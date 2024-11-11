package ru.naumen.naumen_schooldairy.data.dto.student;

import java.io.Serializable;
import java.time.LocalDate;

public record ResponseStudentDto(Long id, String name, String surname, String patronymic,
                                 LocalDate dateOfBirth, String parentContact, String phoneNumber,
                                 ResponseStudentClassDto responseStudentClassDto) implements Serializable {
}
