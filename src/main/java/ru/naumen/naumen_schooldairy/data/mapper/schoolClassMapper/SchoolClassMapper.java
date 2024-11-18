package ru.naumen.naumen_schooldairy.data.mapper.schoolClassMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassDto;
import ru.naumen.naumen_schooldairy.data.dto.schoolClass.ResponseSchoolClassWithScheduleDto;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;

/**
 * Преобразует сущность SchoolClass в DTO.
 */
@Mapper(componentModel = "spring", uses = {DailyScheduleMapper.class})
public interface SchoolClassMapper {

    /**
     * Преобразует сущность SchoolClass в DTO ResponseSchoolClassDto.
     *
     * @param schoolClass сущность SchoolClass, которую необходимо преобразовать.
     * @return объект ResponseSchoolClassDto, представляющий информацию о школьном классе.
     */
    ResponseSchoolClassDto toResponseDto(SchoolClass schoolClass);

    /**
     * Преобразует сущность SchoolClass в DTO ResponseSchoolClassWithScheduleDto с расписанием.
     *
     * @param schoolClass сущность SchoolClass, которую необходимо преобразовать.
     * @return объект ResponseSchoolClassWithScheduleDto, представляющий информацию о школьном классе с расписанием.
     */
    @Mapping(target = "responseDailySchedulesDto", source = "dailySchedules")
    ResponseSchoolClassWithScheduleDto toResponseWithScheduleDto(SchoolClass schoolClass);
}
