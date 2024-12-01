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
import ru.naumen.naumen_schooldairy.service.MarkService;

import java.time.LocalDate;

/**
 * Контроллер для обработки запросов, связанных с оценками.
 */
@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
@Validated
public class MarkController {

    /**
     * Сервис для работы с оценками
     */
    private final MarkService service;

    /**
     * Обновление оценки ученика по заданному предмету и дате.
     *
     * @param subjectId идентификатор предмета, для которого обновляется оценка
     * @param studentId идентификатор студента, чья оценка обновляется
     * @param date      дата, к которой относится оценка
     * @param mark      новая оценка студента (должна быть положительной)
     * @return ResponseEntity без содержимого (204 No Content), если обновление прошло успешно
     */
    @Operation(summary = "Обновление оценки", description = "Обновление оценки ученика по заданному предмету и дате")
    @PutMapping
    public ResponseEntity<Void> updateMark(
            @Parameter(name = "subjectId") @NotNull @Positive @RequestParam("subjectId") Long subjectId,
            @Parameter(name = "studentId") @NotNull @Positive @RequestParam("studentId") Long studentId,
            @Parameter(name = "date") @NotNull @RequestParam("date") LocalDate date,
            @Parameter(name = "mark") @NotNull @Positive @RequestParam("mark") Integer mark
    ) {
        service.updateMark(subjectId, studentId, date, mark);
        return ResponseEntity.ok().build();
    }
}
