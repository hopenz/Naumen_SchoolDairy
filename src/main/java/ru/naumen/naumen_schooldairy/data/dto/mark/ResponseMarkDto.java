package ru.naumen.naumen_schooldairy.data.dto.mark;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс для ответа с информацией об оценке.
 * Этот класс содержит идентификатор оценки, значение оценки и дату выставления оценки.
 *
 * @param id        идентификатор оценки
 * @param mark      значение оценки
 * @param gradeDate дата, когда была выставлена оценка
 */
public record ResponseMarkDto(Long id, Integer mark, LocalDate gradeDate) implements Serializable {
}