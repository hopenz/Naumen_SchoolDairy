package ru.naumen.naumen_schooldairy.data.dto.student;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс для ответа с информацией о студенте.
 * Этот класс содержит идентификатор студента, его имя, фамилию, отчество,
 * дату рождения, контактные данные родителя и номер телефона.
 *
 * @param id            идентификатор студента
 * @param name          имя студента
 * @param surname       фамилия студента
 * @param patronymic    отчество студента
 * @param dateOfBirth   дата рождения студента
 * @param parentContact контактные данные родителя студента
 * @param phoneNumber   номер телефона студента
 */
public record ResponseStudentDto(Long id, String name, String surname, String patronymic, LocalDate dateOfBirth,
                                 String parentContact, String phoneNumber) implements Serializable {
}