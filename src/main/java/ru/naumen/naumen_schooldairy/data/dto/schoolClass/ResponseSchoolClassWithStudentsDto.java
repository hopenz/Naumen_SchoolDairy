package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.SchoolClass}
 */
public record ResponseSchoolClassWithStudentsDto(Long id, String className,
                                                 Set<ResponseStudentDto> students) implements Serializable {
}