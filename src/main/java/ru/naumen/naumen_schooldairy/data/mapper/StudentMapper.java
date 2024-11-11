package ru.naumen.naumen_schooldairy.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.entity.Student;

@Mapper(componentModel = "spring", uses = {StudentClassMapper.class})
public interface StudentMapper {

    Student toEntity(RequestStudentDto requestStudentDto);

    @Mapping(target = "responseStudentClassDto", source = "schoolClass")
    ResponseStudentDto toResponseDto(Student student);
}
