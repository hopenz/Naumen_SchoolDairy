package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.entity.Homework;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;
import ru.naumen.naumen_schooldairy.data.repository.HomeworkRepository;
import ru.naumen.naumen_schooldairy.data.repository.LessonRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Сервис для управления домашними заданиями
 */
@Service
@RequiredArgsConstructor
public class HomeworkService {

    /**
     * Репозиторий для работы с домашними заданиями
     */
    private final HomeworkRepository homeworkRepository;

    /**
     * Репозиторий для работы с уроками
     */
    private final LessonRepository lessonRepository;

    /**
     * Обновляет домашнее задание для указанного урока.
     * Удаляет все существующие домашние задания и создает новое с заданным описанием.
     *
     * @param description описание домашнего задания.
     * @param lessonId    идентификатор урока, к которому будет привязано домашнее задание.
     */
    @Transactional
    public void updateHomework(String description, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException("Lesson", lessonId));
        homeworkRepository.deleteAll(lesson.getHomework());

        Homework homework = new Homework();
        homework.setDescription(description);
        homework.setLesson(lesson);
        lesson.setHomework(new HashSet<>(Set.of(homework)));
        lessonRepository.save(lesson);
    }
}
