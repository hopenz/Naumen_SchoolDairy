package ru.naumen.naumen_schooldairy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.repository.DailyScheduleRepository;
import ru.naumen.naumen_schooldairy.service.DailyScheduleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dailySchedule")
@RequiredArgsConstructor
public class DailyScheduleController {

    private final DailyScheduleService dailyScheduleService;

    //Надо будет переделать, так как выводится расписание класса
    @GetMapping("/{date},/{classId}")
    public ResponseEntity<ResponseDailyScheduleDto> getAllSchedule(LocalDate date, Long classId){
        ResponseDailyScheduleDto dailySchedule = dailyScheduleService.getScheduleByDateAndClassId(date,classId);
        return  ResponseEntity.ok(dailySchedule);
    }
}
