package ru.naumen.naumen_schooldairy.data.mapper.dailySchedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;
import ru.naumen.naumen_schooldairy.data.mapper.lesson.LessonMapper;

@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface DailyScheduleMapper {

    @Mapping(target = "responseLessonDto", source = "lessons")
    ResponseDailyScheduleDto toResponseDto(DailySchedule dailySchedule);
}
