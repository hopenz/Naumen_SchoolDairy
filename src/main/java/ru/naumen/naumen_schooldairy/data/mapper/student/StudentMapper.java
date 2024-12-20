package ru.naumen.naumen_schooldairy.data.mapper.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.student.*;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.mark.MarkMapper;
import ru.naumen.naumen_schooldairy.data.mapper.schoolClass.SchoolClassMapper;

/**
 * Mapper для преобразования сущности Student в различные DTO и обратно
 */
@Mapper(componentModel = "spring", uses = {SchoolClassMapper.class, MarkMapper.class})
public interface StudentMapper {

    /**
     * Преобразует DTO RequestStudentDto в сущность Student.
     *
     * @param requestStudentDto DTO, содержащий данные о студенте, которые необходимо преобразовать в сущность.
     * @return сущность Student, представляющая студента.
     */
    Student toEntity(RequestStudentDto requestStudentDto);

    /**
     * Преобразует сущность Student в DTO ResponseStudentDto.
     *
     * @param student сущность Student, которую необходимо преобразовать.
     * @return объект ResponseStudentDto, представляющий информацию о студенте.
     */
    @Mapping(target = "responseSchoolClassDto", source = "schoolClass")
    ResponseStudentWithClassDto toResponseDto(Student student);

    /**
     * Преобразует сущность Student в DTO ResponseStudentWithScheduleDto с расписанием.
     *
     * @param student сущность Student, которую необходимо преобразовать.
     * @return объект ResponseStudentWithScheduleDto, представляющий информацию о студенте с расписанием.
     */
    @Mapping(target = "responseSchoolClassWithScheduleDto", source = "schoolClass")
    ResponseStudentWithScheduleDto toResponseWithSchedule(Student student);

    /**
     * Преобразует сущность Student в DTO ResponseStudentWithSubjectsAndMarksDto с предметами и оценками.
     *
     * @param student сущность Student, которую необходимо преобразовать.
     * @return объект ResponseStudentWithSubjectsAndMarksDto, представляющий информацию о студенте с предметами и оценками.
     */
    @Mapping(target = "responseMarkWithSubjectDto", source = "marks")
    ResponseStudentWithSubjectsAndMarksDto toResponseWithSubjectsAndMarks(Student student);

    /**
     * Преобразует сущность Student в DTO ResponseStudentWithMarksDto с оценками.
     *
     * @param student сущность Student, которую необходимо преобразовать.
     * @return объект ResponseStudentWithMarksDto, представляющий информацию о студенте с оценками.
     */
    @Mapping(target = "marks", source = "marks")
    ResponseStudentWithMarksDto toResponseWithMarks(Student student);

    /**
     * Преобразует сущность Student в DTO ResponseStudentDto.
     *
     * @param student сущность Student, которую необходимо преобразовать.
     * @return объект ResponseStudentDto, представляющий информацию о студенте.
     */
    ResponseStudentDto toDto(Student student);

}
