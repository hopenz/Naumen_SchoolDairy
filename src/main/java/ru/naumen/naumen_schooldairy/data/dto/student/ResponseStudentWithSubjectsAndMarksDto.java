package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;

import java.io.Serializable;
import java.util.List;

public record ResponseStudentWithSubjectsAndMarksDto(Long id, List<ResponseMarkDto> responseMarkDto)
        implements Serializable {
}
