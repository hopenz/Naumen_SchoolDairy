package ru.naumen.naumen_schooldairy.data.mapper.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithSubjectsAndMarksDto;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.mark.MarkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.schoolClassMapper.SchoolClassMapper;

@Mapper(componentModel = "spring", uses = {SchoolClassMapper.class, MarkMapper.class})
public interface StudentMapper {

    Student toEntity(RequestStudentDto requestStudentDto);

    @Mapping(target = "responseSchoolClassDto", source = "schoolClass")
    ResponseStudentDto toResponseDto(Student student);

    @Mapping(target = "responseSchoolClassWithScheduleDto", source = "schoolClass")
    ResponseStudentWithScheduleDto toResponseWithSchedule(Student student);

    @Mapping(target = "responseMarkDto", source = "marks")
    ResponseStudentWithSubjectsAndMarksDto toResponseWithSubjectsAndMarks(Student student);
}
