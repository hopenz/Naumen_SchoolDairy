package ru.naumen.naumen_schooldairy.data.mapper.dailySchedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDailyScheduleWithLessonDto;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDatesOfLessonsDto;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;
import ru.naumen.naumen_schooldairy.data.mapper.lesson.LessonMapper;

/**
 * Mapper для преобразования сущности DailySchedule.
 */
@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface DailyScheduleMapper {

    /**
     * Преобразует сущность DailySchedule в DTO ResponseDailyScheduleDto.
     *
     * @param dailySchedule сущность DailySchedule, которую необходимо преобразовать.
     * @return объект ResponseDailyScheduleDto, представляющий расписание на день.
     */
    @Mapping(target = "responseLessonDto", source = "lessons")
    ResponseDailyScheduleDto toResponseDto(DailySchedule dailySchedule);

    /**
     * Преобразует сущность DailySchedule в DTO ResponseDailyScheduleWithLessonDto,
     * включая информацию о уроках.
     *
     * @param dailySchedule сущность DailySchedule, которую необходимо преобразовать.
     * @return объект ResponseDailyScheduleWithLessonDto с информацией о расписании и уроках.
     */
    @Mapping(target = "lessons", source = "lessons")
    ResponseDailyScheduleWithLessonDto toResponseWithLessonsDto(DailySchedule dailySchedule);

    /**
     * Преобразует сущность DailySchedule в DTO ResponseDatesOfLessonsDto,
     * содержащий даты уроков.
     *
     * @param dailySchedule сущность DailySchedule, которую необходимо преобразовать.
     * @return объект ResponseDatesOfLessonsDto с датами уроков.
     */
    ResponseDatesOfLessonsDto toResponseDatesOfLessonsDto(DailySchedule dailySchedule);

}
