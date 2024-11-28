package ru.naumen.naumen_schooldairy.data.dto.lesson;

import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;

import java.io.Serializable;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Lesson}
 */
public record ResponseLessonWithSubjectDto(Long id, ResponseSubjectDto subject) implements Serializable {
}