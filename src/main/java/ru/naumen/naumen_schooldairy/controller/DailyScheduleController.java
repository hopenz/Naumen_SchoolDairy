package ru.naumen.naumen_schooldairy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.RequestDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDatesOfLessonsDto;
import ru.naumen.naumen_schooldairy.service.DailyScheduleService;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с расписанием уроков
 */
@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Validated
public class DailyScheduleController {

    /**
     * Сервис для управления расписанием уроков
     */
    private final DailyScheduleService dailyScheduleService;

    /**
     * Получение дат уроков для заданного класса и предмета.
     *
     * @param classId   идентификатор класса, для которого запрашиваются даты уроков
     * @param subjectId идентификатор предмета, для которого запрашиваются даты уроков
     * @return ResponseEntity со списком дат уроков
     */
    @Operation(summary = "Даты уроков", description = "Получение дат уроков для заданного класса и предмета.")
    @GetMapping("/lessons/dates")
    public ResponseEntity<List<ResponseDatesOfLessonsDto>> getDatesOfLessons
    (@Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId,
     @Parameter(name = "subjectId") @NotNull @Positive @RequestParam("subjectId") Long subjectId) {
        return ResponseEntity.ok(dailyScheduleService.getDatesOfLessons(classId, subjectId));
    }

    /**
     * Создание расписания на день для заданного класса.
     *
     * @param schedule объект запроса с данными расписания
     * @param classId  идентификатор класса, для которого создается расписание
     * @return ResponseEntity без содержимого (204 No Content)
     */
    @Operation(summary = "Создание расписания на день", description = "Создание расписания на день для заданного класса.")
    @PostMapping
    public ResponseEntity<Void> createSchedule(
            @RequestBody RequestDailyScheduleDto schedule,
            @Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId) {
        dailyScheduleService.createSchedule(schedule, classId);
        return ResponseEntity.ok().build();
    }
}
