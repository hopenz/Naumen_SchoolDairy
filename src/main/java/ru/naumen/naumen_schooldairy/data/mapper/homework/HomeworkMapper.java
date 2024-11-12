package ru.naumen.naumen_schooldairy.data.mapper.homework;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.homework.ResponseHomeworkDto;
import ru.naumen.naumen_schooldairy.data.entity.Homework;

@Mapper(componentModel = "spring")
public interface HomeworkMapper {

    ResponseHomeworkDto toResponseDto(Homework homework);
}