package ru.naumen.naumen_schooldairy.data.mapper.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.lesson.ResponseLessonDto;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;
import ru.naumen.naumen_schooldairy.data.mapper.homework.HomeworkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.mark.MarkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;

@Mapper(componentModel = "spring", uses = {MarkMapper.class, SubjectMapper.class, HomeworkMapper.class})
public interface LessonMapper {

    @Mapping(target = "responseMarkDto", source = "marks")
    @Mapping(target = "responseSubjectDto", source = "subject")
    @Mapping(target = "responseHomeworkDto", source = "homework")
    ResponseLessonDto toResponseDto(Lesson lesson);
}
