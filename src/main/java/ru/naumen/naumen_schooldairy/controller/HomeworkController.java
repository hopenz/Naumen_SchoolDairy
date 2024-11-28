package ru.naumen.naumen_schooldairy.controller;

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

@RestController
@RequestMapping("/api/homeworks")
@RequiredArgsConstructor
@Validated
public class HomeworkController {

    private final HomeworkService service;


    @PutMapping
    public ResponseEntity<Void> updateHomework(
            @Parameter(name = "lessonId") @NotNull @Positive @RequestParam("lessonId") Long lessonId,
            @Parameter(name = "description") @NotNull @RequestParam("description") String description
    ) {
        service.updateHomework(description, lessonId);
        return ResponseEntity.ok().build();
    }
}
