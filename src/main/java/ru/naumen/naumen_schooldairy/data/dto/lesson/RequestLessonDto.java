package ru.naumen.naumen_schooldairy.data.dto.lesson;

import java.io.Serializable;

/**
 * DTO для запроса информации об уроке.
 * Этот класс представляет собой запись, содержащую информацию о номере урока,
 * названии предмета и описании домашнего задания.
 *
 * @param lessonNumber        номер урока в расписании
 * @param subjectName         название предмета, к которому относится урок
 * @param homeworkDescription описание домашнего задания, связанного с уроком
 */
public record RequestLessonDto(Integer lessonNumber, String subjectName,
                               String homeworkDescription) implements Serializable {
}