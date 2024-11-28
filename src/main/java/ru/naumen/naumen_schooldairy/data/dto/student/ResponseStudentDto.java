package ru.naumen.naumen_schooldairy.data.dto.student;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Student}
 */
public record ResponseStudentDto(Long id, String name, String surname, String patronymic, LocalDate dateOfBirth,
                                 String parentContact, String phoneNumber) implements Serializable {
}