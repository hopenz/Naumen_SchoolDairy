package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.repository.DailyScheduleRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyScheduleService {

    private final DailyScheduleRepository dailyScheduleRepository;

    private final DailyScheduleMapper dailyScheduleMapper;

    @Transactional
    public ResponseDailyScheduleDto getScheduleByDateAndClassId(LocalDate date, Long classId) {
        return dailyScheduleMapper.toResponseDto(dailyScheduleRepository
                .findDailyScheduleByDateDayAndClassField_Id(date, classId));

    }
}
