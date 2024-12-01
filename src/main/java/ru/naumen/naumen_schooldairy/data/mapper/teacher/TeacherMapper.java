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
 * Mapper для преобразования сущностей Teacher в соответствующие DTO
 */
@Mapper(componentModel = "spring", uses = {SchoolClassMapper.class, DailyScheduleMapper.class})
public interface TeacherMapper {

    /**
     * Преобразует сущность Teacher в DTO ResponseTeacherDto.
     *
     * @param teacher сущность Teacher, которую необходимо преобразовать.
     * @return объект ResponseTeacherDto, представляющий информацию об учителе.
     */
    ResponseTeacherDto toResponseDto(Teacher teacher);

    /**
     * Преобразует DTO RequestTeacherDto в сущность Teacher.
     *
     * @param requestTeacherDto DTO, содержащий данные об учителе, которые необходимо преобразовать в сущность.
     * @return сущность Teacher, представляющая учителя.
     */
    Teacher toEntity(RequestTeacherDto requestTeacherDto);

    /**
     * Преобразует сущность Teacher в DTO ResponseTeacherWithStudentsDto,
     * включая информацию о классах и учениках.
     *
     * @param teacher сущность Teacher, которую необходимо преобразовать.
     * @return объект ResponseTeacherWithStudentsDto, представляющий информацию об учителе с учениками.
     */
    @Mapping(target = "schoolClasses", source = "schoolClasses")
    ResponseTeacherWithStudentsDto toDto(Teacher teacher);

    /**
     * Преобразует сущность Teacher в DTO ResponseTeacherWithDateOfLessonsDto,
     * включая информацию о классах и расписании уроков.
     *
     * @param teacher сущность Teacher, которую необходимо преобразовать.
     * @return объект ResponseTeacherWithDateOfLessonsDto, представляющий информацию об учителе с расписанием уроков.
     */
    @Mapping(target = "schoolClasses", source = "schoolClasses")
    ResponseTeacherWithDateOfLessonsDto toDtoWIthDateOfLessons(Teacher teacher);

}
