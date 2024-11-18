package ru.naumen.naumen_schooldairy.data.dto.schoolClass;

import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO для представления информации о школьном классе с расписанием.
 *
 * @param id                        уникальный идентификатор школьного класса.
 * @param responseDailySchedulesDto набор объектов ResponseDailyScheduleDto,
 *                                  представляющих расписание для данного класса.
 */
public record ResponseSchoolClassWithScheduleDto(Long id, Set<ResponseDailyScheduleDto> responseDailySchedulesDto)
        implements Serializable {
}
