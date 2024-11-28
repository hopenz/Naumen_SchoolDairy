package ru.naumen.naumen_schooldairy.data.dto.mark;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Mark}
 */
public record ResponseMarkDto(Long id, Integer mark, LocalDate gradeDate) implements Serializable {
}