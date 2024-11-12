package ru.naumen.naumen_schooldairy.data.mapper.subject;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    ResponseSubjectDto toResponseDto (Subject subject);
}
