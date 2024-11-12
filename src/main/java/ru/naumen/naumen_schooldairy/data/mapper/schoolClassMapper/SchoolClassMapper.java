package ru.naumen.naumen_schooldairy.data.mapper.schoolClassMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithScheduleDto;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;

@Mapper(componentModel = "spring", uses = {DailyScheduleMapper.class})
public interface SchoolClassMapper {

    ResponseSchoolClassDto toResponseDto(SchoolClass schoolClass);

    @Mapping(target = "responseDailySchedulesDto", source = "dailySchedules")
    ResponseSchoolClassWithScheduleDto toResponseWithScheduleDto(SchoolClass schoolClass);
}
