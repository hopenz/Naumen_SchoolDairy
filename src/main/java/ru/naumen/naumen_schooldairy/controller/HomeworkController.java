package ru.naumen.naumen_schooldairy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.naumen_schooldairy.service.HomeworkService;

/**
 * Контроллер для обработки запросов, связанных с домашними заданиями
 */
@RestController
@RequestMapping("/api/homeworks")
@RequiredArgsConstructor
@Validated
public class HomeworkController {

    /**
     * Сервис для работы с домашними заданиями
     */
    private final HomeworkService service;

    /**
     * Обновление домашнего задания для заданного урока.
     *
     * @param lessonId    идентификатор урока, к которому относится домашнее задание
     * @param description новое описание домашнего задания
     * @return ResponseEntity без содержимого (204 No Content), если обновление прошло успешно
     */
    @Operation(summary = "Обновление домашнего задания", description = "Обновление домашнего задания для заданного урока")
    @PutMapping
    public ResponseEntity<Void> updateHomework(
            @Parameter(name = "lessonId") @NotNull @Positive @RequestParam("lessonId") Long lessonId,
            @Parameter(name = "description") @NotNull @RequestParam("description") String description
    ) {
        service.updateHomework(description, lessonId);
        return ResponseEntity.ok().build();
    }
}
