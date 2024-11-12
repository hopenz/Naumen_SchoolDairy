package ru.naumen.naumen_schooldairy.data.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public record RequestStudentDto(@NotBlank(message = "Имя не может быть пустым") String name,
                                @NotBlank(message = "Фамилия не может быть пустой") String surname,
                                @NotBlank(message = "Отчество не может быть пустым") String patronymic,
                                @NotNull(message = "Дата рождения не может быть пустой") LocalDate dateOfBirth,
                                @NotBlank(message = "Контакт родителя не может быть пустым") String parentContact,
                                @NotBlank(message = "Номер телефона не может быть пустым") String phoneNumber)
        implements Serializable {
}
