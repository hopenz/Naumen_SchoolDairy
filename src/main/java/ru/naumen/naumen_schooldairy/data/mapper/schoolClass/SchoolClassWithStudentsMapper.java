package ru.naumen.naumen_schooldairy.data.mapper.schoolClass;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithStudentsDto;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;

/**
 * TODO
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface SchoolClassWithStudentsMapper {

    /**
     * TODO
     *
     * @param schoolClass
     * @return
     */
    @Mapping(target = "students", source = "students")
    ResponseSchoolClassWithStudentsDto toDto(SchoolClass schoolClass);
}
