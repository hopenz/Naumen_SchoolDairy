package ru.naumen.naumen_schooldairy.data.mapper.mark;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;
import ru.naumen.naumen_schooldairy.data.entity.Mark;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class})
public interface MarkMapper {

    @Mapping(target = "responseSubjectDto", source = "subject")
    ResponseMarkDto toResponseMarkWithSubjectDto(Mark mark);


}
