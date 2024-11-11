package ru.naumen.naumen_schooldairy.data.mapper;

import org.mapstruct.Mapper;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentClassDto;

@Mapper(componentModel = "spring")
public interface StudentClassMapper {

    ResponseStudentClassDto toResponseDto (StudentClassMapper studentClassMapper);
}
