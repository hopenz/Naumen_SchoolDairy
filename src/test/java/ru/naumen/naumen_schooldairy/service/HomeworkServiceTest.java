package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;
import ru.naumen.naumen_schooldairy.data.repository.HomeworkRepository;
import ru.naumen.naumen_schooldairy.data.repository.LessonRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирует сервис для управления домашними заданиями.
 */
public class HomeworkServiceTest {

    /**
     * Задача сервиса для управления домашними заданиями.
     */
    @Mock
    private HomeworkRepository homeworkRepository;

    /**
     * Заглушка для работы с уроками.
     */
    @Mock
    private LessonRepository lessonRepository;

    /**
     * Внедрение заглушек и создание экземпляра сервиса.
     */
    @InjectMocks
    private HomeworkService homeworkService;

    /**
     * Инициализация моков перед выполнением каждого теста
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирует обновление домашнего задания для существующего урока.
     * Проверяет, что новое домашнее задание создается и сохраняется корректно.
     */
    @Test
    public void testUpdateHomework_Success() {
        Long lessonId = 1L;
        String homeworkDescription = "tasks";

        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        lesson.setHomework(new HashSet<>());

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));

        homeworkService.updateHomework(homeworkDescription, lessonId);

        assertEquals(1, lesson.getHomework().size());
        assertEquals(homeworkDescription, lesson.getHomework().iterator().next().getDescription());
        verify(lessonRepository).save(lesson);
    }

    /**
     * Тестирует обновление домашнего задания для несуществующего урока.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testUpdateHomework_LessonNotFound() {
        Long lessonId = 1L;
        String homeworkDescription = "tasks";

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            homeworkService.updateHomework(homeworkDescription, lessonId);
        });

        assertEquals("Lesson with id 1 not found", exception.getMessage());
    }
}
