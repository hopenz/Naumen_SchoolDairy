package ru.naumen.naumen_schooldairy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.service.SubjectService;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с предметами
 */
@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
@Validated
public class SubjectController {

    /**
     * Сервис для работы с предметами
     */
    private final SubjectService subjectService;

    /**
     * Получение списка предметов для заданного класса.
     *
     * @param classId идентификатор класса, для которого запрашиваются предметы
     * @return ResponseEntity со списком предметов для указанного класса
     */
    @Operation(summary = "Список предметов класса", description = "Получение списка предметов для заданного класса.")
    @GetMapping("/class")
    public ResponseEntity<List<ResponseSubjectDto>> getStudentsMarks
    (@Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId) {
        return ResponseEntity.ok(subjectService.getSubjectsForClass(classId));
    }
}
