package ru.naumen.naumen_schooldairy.data.mapper.schoolClass;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithStudentsDto;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;

/**
 * Mapper для преобразования сущностей SchoolClass с учениками в соответствующие DTO
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface SchoolClassWithStudentsMapper {

    /**
     * Преобразует сущность SchoolClass в DTO ResponseSchoolClassWithStudentsDto,
     * включая информацию о студентах.
     *
     * @param schoolClass сущность SchoolClass, которую необходимо преобразовать.
     * @return объект ResponseSchoolClassWithStudentsDto, представляющий информацию о школьном классе с учениками.
     */
    @Mapping(target = "students", source = "students")
    ResponseSchoolClassWithStudentsDto toDto(SchoolClass schoolClass);
}
