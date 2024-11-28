package ru.naumen.naumen_schooldairy.data.mapper.teacher;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.teacher.RequestTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithDateOfLessonsDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithStudentsDto;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.mapper.schoolClass.SchoolClassMapper;

/**
 * TODO
 */
@Mapper(componentModel = "spring", uses = {SchoolClassMapper.class, DailyScheduleMapper.class})
public interface TeacherMapper {

    /**
     * TODO
     *
     * @param teacher
     * @return
     */
    ResponseTeacherDto toResponseDto(Teacher teacher);

    /**
     * TODO
     *
     * @param requestTeacherDto
     * @return
     */
    Teacher toEntity(RequestTeacherDto requestTeacherDto);

    /**
     * TODO
     *
     * @param teacher
     * @return
     */
    @Mapping(target = "schoolClasses", source = "schoolClasses")
    ResponseTeacherWithStudentsDto toDto(Teacher teacher);

    /**
     * TODO
     *
     * @param teacher
     * @return
     */
    @Mapping(target = "schoolClasses", source = "schoolClasses")
    ResponseTeacherWithDateOfLessonsDto toDtoWIthDateOfLessons(Teacher teacher);

}
