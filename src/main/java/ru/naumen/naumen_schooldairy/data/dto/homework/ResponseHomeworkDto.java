package ru.naumen.naumen_schooldairy.data.dto.homework;

/**
 * DTO для представления информации о домашнем задании.
 *
 * @param id          идентификатор домашнего задания
 * @param description описание домашнего задания
 */
public record ResponseHomeworkDto(Long id, String description) {
}
