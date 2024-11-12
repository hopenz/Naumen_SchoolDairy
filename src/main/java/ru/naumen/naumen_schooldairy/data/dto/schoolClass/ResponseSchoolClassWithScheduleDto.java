package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;

import java.io.Serializable;
import java.util.Set;

public record ResponseSchoolClassWithScheduleDto(Long id, Set<ResponseDailyScheduleDto> responseDailySchedulesDto)
        implements Serializable {
}
