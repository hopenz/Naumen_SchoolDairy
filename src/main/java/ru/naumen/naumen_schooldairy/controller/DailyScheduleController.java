package ru.naumen.naumen_schooldairy.controller;

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

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Validated
public class DailyScheduleController {

    private final DailyScheduleService dailyScheduleService;

    @GetMapping("/lessons/dates")
    public ResponseEntity<List<ResponseDatesOfLessonsDto>> getDatesOfLessons
            (@Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId,
             @Parameter(name = "subjectId") @NotNull @Positive @RequestParam("subjectId") Long subjectId) {
        return ResponseEntity.ok(dailyScheduleService.getDatesOfLessons(classId, subjectId));
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(
            @RequestBody RequestDailyScheduleDto schedule,
            @Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId) {
        dailyScheduleService.createSchedule(schedule, classId);
        return ResponseEntity.ok().build();
    }
}
