package ru.naumen.naumen_schooldairy.data.dto.lesson;

import ru.naumen.naumen_schooldairy.data.dto.homework.ResponseHomeworkDto;
import ru.naumen.naumen_schooldairy.data.dto.mark.ResponseMarkDto;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;
import java.util.Set;

public record ResponseLessonDto(Long id, Set<ResponseMarkDto> responseMarkDto,
                                ResponseSubjectDto responseSubjectDto,
                                Set<ResponseHomeworkDto> responseHomeworkDto, Integer lessonNumber) implements Serializable {
}
