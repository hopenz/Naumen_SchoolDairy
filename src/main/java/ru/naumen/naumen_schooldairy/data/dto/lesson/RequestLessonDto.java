package ru.naumen.naumen_schooldairy.data.dto.lesson;

import java.io.Serializable;

/**
 * DTO for {@link ru.naumen.naumen_schooldairy.data.entity.Lesson}
 */
public record RequestLessonDto(Integer lessonNumber, String subjectName,
                               String homeworkDescription) implements Serializable {
}