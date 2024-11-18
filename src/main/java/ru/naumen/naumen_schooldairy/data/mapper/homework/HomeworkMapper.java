package ru.naumen.naumen_schooldairy.data.mapper.homework;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.homework.ResponseHomeworkDto;
import ru.naumen.naumen_schooldairy.data.entity.Homework;

/**
 * Mapper для преобразования сущности Homework
 */
@Mapper(componentModel = "spring")
public interface HomeworkMapper {

    /**
     * Преобразует сущность Homework в DTO ResponseHomeworkDto.
     *
     * @param homework сущность Homework, которую необходимо преобразовать.
     * @return объект ResponseHomeworkDto, представляющий домашнее задание.
     */
    ResponseHomeworkDto toResponseDto(Homework homework);
}