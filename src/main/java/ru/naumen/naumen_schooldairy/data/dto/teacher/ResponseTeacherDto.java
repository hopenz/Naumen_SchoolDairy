package ru.naumen.naumen_schooldairy.data.dto.teacher;

import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Teacher}
 */
public record ResponseTeacherDto(Long id, String name, String surname, String patronymic, String phoneNumber,
                                 Set<ResponseSchoolClassDto> schoolClasses) implements Serializable {
}