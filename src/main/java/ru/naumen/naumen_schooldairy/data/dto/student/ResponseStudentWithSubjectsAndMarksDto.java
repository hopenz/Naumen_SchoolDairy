package ru.naumen.naumen_schooldairy.data.dto.student;

import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO для представления информации о студенте с его оценками и предметами.
 *
 * @param id              уникальный идентификатор студента.
 * @param responseMarkDto список объектов ResponseMarkDto, представляющих оценки студента по предметам.
 */
public record ResponseStudentWithSubjectsAndMarksDto(Long id, List<ResponseMarkDto> responseMarkDto)
        implements Serializable {
}
