package ru.naumen.naumen_schooldairy.data.mapper.teacher;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.mapper.schoolClass.SchoolClassMapper;

@Mapper(componentModel = "spring", uses = {SchoolClassMapper.class, DailyScheduleMapper.class})
public interface TeacherMapper {

    ResponseTeacherDto toResponseDto(Teacher teacher);
}
