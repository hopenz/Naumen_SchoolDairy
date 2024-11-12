package ru.naumen.naumen_schooldairy.data.mapper.mark;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;
import ru.naumen.naumen_schooldairy.data.entity.Mark;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    ResponseMarkDto toResponseDto (Mark mark);
}
