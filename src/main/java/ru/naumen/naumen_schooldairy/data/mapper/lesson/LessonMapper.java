package ru.naumen.naumen_schooldairy.data.mapper.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonDto;
import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonWithSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.mapper.homework.HomeworkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.mark.MarkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;

/**
 * Mapper для преобразования сущности Lesson в
 */
@Mapper(componentModel = "spring", uses = {MarkMapper.class, SubjectMapper.class, HomeworkMapper.class, DailyScheduleMapper.class})
public interface LessonMapper {

    /**
     * Преобразует сущность Lesson в DTO ResponseLessonDto.
     *
     * @param lesson сущность Lesson, которую необходимо преобразовать.
     * @return объект ResponseLessonDto, представляющий занятие.
     */
    @Mapping(target = "responseMarkWithSubjectDto", source = "marks")
    @Mapping(target = "responseSubjectDto", source = "subject")
    @Mapping(target = "responseHomeworkDto", source = "homework")
    ResponseLessonDto toResponseDto(Lesson lesson);

    /**
     * Преобразует сущность Lesson в DTO ResponseLessonWithSubjectDto,
     * включая информацию о предмете.
     *
     * @param lesson сущность Lesson, которую необходимо преобразовать.
     * @return объект ResponseLessonWithSubjectDto с информацией о занятии и предмете.
     */
    @Mapping(target = "subject", source = "subject")
    ResponseLessonWithSubjectDto toDto(Lesson lesson);

}
