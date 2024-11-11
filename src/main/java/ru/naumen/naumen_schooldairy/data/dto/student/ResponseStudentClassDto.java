package ru.naumen.naumen_schooldairy.data.dto.student;

import java.io.Serializable;

public record ResponseStudentClassDto (Long id, String className) implements Serializable {
}
