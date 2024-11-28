package ru.naumen.naumen_schooldairy.data.dto.teacher;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * TODO
 */
public record RequestTeacherDto(@NotBlank(message = "Имя не может быть пустым") String name,
                                @NotBlank(message = "Фамилия не может быть пустой") String surname,
                                @NotBlank(message = "Отчество не может быть пустым") String patronymic,
                                @NotBlank(message = "Номер телефона не может быть пустым") String phoneNumber)
        implements Serializable {
}
