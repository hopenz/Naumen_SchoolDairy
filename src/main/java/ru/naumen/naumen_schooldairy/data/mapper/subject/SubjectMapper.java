package ru.naumen.naumen_schooldairy.data.mapper.subject;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

/**
 * Mapper для преобразования сущности Subject в DTO
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper {

    /**
     * Преобразует сущность Subject в DTO ResponseSubjectDto.
     *
     * @param subject сущность Subject, которую необходимо преобразовать.
     * @return объект ResponseSubjectDto, представляющий информацию о предмете.
     */
    ResponseSubjectDto toResponseDto(Subject subject);
}
