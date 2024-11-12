package ru.naumen.naumen_schooldairy.data.dto.mark;

import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;
import java.time.LocalDate;

public record ResponseMarkDto(Long id, Integer mark, LocalDate gradeDate,
                              ResponseSubjectDto responseSubjectDto) implements Serializable {

}
