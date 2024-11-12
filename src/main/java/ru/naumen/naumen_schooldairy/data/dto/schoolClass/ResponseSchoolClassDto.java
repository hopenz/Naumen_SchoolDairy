package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import java.io.Serializable;

public record ResponseSchoolClassDto(Long id, String className) implements Serializable {
}
