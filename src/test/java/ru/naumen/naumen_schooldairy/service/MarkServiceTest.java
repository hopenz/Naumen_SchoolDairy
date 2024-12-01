package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.entity.*;
import ru.naumen.naumen_schooldairy.data.repository.*;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса для работы с оценками студентов.
 */
public class MarkServiceTest {

    /**
     * Заглушка для репозитория оценок
     */
    @Mock
    private MarkRepository markRepository;

    /**
     * Заглушка для репозитория уроков
     */
    @Mock
    private LessonRepository lessonRepository;

    /**
     * Заглушка для репозитория расписания
     */
    @Mock
    private DailyScheduleRepository dailyScheduleRepository;

    /**
     * Заглушка для репозитория студентов
     */
    @Mock
    private StudentRepository studentRepository;

    /**
     * Заглушка для репозитория предметов
     */
    @Mock
    private SubjectRepository subjectRepository;

    /**
     * Внедрение заглушек и создание экземпляра сервиса
     */
    @InjectMocks
    private MarkService markService;

    /**
     * Инициализация моков перед выполнением каждого теста
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом
    }

    /**
     * Тестирует обновление оценки студента по указанному предмету на заданную дату.
     * Проверяет, что новая оценка сохраняется и старая удаляется.
     */
    @Test
    public void testUpdateMark_Success() {
        Long studentId = 1L;
        Long subjectId = 1L;
        LocalDate date = LocalDate.now();
        Integer newMarkValue = 5;

        Student student = new Student();
        student.setId(studentId);
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(1L);
        student.setSchoolClass(schoolClass);

        Subject subject = new Subject();
        subject.setId(subjectId);

        DailySchedule dailySchedule = new DailySchedule();
        dailySchedule.setId(1L);

        Lesson lesson = new Lesson();
        lesson.setId(1L);

        Mark oldMark = new Mark();
        oldMark.setStudent(student);
        oldMark.setSubject(subject);
        oldMark.setGradeDate(date);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(dailyScheduleRepository.findDailyScheduleByDateDayAndClassField_Id(date, schoolClass.getId()))
                .thenReturn(dailySchedule);
        when(lessonRepository.findLessonByDailySchedule_IdAndSubject_Id(dailySchedule.getId(), subjectId))
                .thenReturn(lesson);
        when(markRepository.findMarkByStudent_IdAndSubject_IdAndGradeDate(studentId, subjectId, date))
                .thenReturn(oldMark);

        markService.updateMark(subjectId, studentId, date, newMarkValue);

        verify(markRepository).delete(oldMark);

        Mark newMark = new Mark();
        newMark.setMark(newMarkValue);
        newMark.setSubject(subject);
        newMark.setStudent(student);
        newMark.setGradeDate(date);
        newMark.setLesson(lesson);

        verify(markRepository).save(any(Mark.class));
    }

    /**
     * Тестирует обновление оценки студента, когда студент не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testUpdateMark_StudentNotFound() {
        Long studentId = 1L;
        Long subjectId = 1L;
        LocalDate date = LocalDate.now();

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            markService.updateMark(subjectId, studentId, date, 5);
        });

        assertEquals("Student with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует обновление оценки студента, когда предмет не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testUpdateMark_SubjectNotFound() {
        Long studentId = 1L;
        Long subjectId = 1L;
        LocalDate date = LocalDate.now();

        Student student = new Student();
        student.setId(studentId);


        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            markService.updateMark(subjectId, studentId, date, 5);
        });

        assertEquals("Subject with id 1 not found", exception.getMessage());
    }
}
