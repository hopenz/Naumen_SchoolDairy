package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Student}
 */
public record ResponseStudentWithMarksDto(Long id, String name, String surname, String patronymic,
                                          Set<ResponseMarkDto> marks) implements Serializable {
}