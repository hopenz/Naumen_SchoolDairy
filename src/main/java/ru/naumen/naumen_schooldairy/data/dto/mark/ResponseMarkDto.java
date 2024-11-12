package ru.naumen.naumen_schooldairy.data.dto.mark;

import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;

import java.io.Serializable;
import java.time.LocalDate;

public record ResponseMarkDto(Long id, Integer mark, LocalDate gradeDate) implements Serializable {
}
