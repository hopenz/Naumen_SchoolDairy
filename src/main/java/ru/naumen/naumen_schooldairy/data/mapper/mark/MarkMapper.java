package ru.naumen.naumen_schooldairy.data.mapper.mark;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;
import ru.naumen.naumen_schooldairy.data.entity.Mark;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;

/**
 * Mapper для преобразования сущности Mark в DTO
 */
@Mapper(componentModel = "spring", uses = {SubjectMapper.class})
public interface MarkMapper {

    /**
     * Преобразует сущность Mark в DTO ResponseMarkDto с учетом предмета.
     *
     * @param mark сущность Mark, которую необходимо преобразовать.
     * @return объект ResponseMarkDto, представляющий оценку с информацией о предмете.
     */
    @Mapping(target = "responseSubjectDto", source = "subject")
    ResponseMarkDto toResponseMarkWithSubjectDto(Mark mark);


}
