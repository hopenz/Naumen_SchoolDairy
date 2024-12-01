package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.RequestDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.lesson.RequestLessonDto;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.repository.DailyScheduleRepository;
import ru.naumen.naumen_schooldairy.data.repository.SchoolClassRepository;
import ru.naumen.naumen_schooldairy.data.repository.SubjectRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса для управления расписанием занятий
 */
public class DailyScheduleServiceTest {

    /**
     * Заглушка для DailyScheduleMapper
     */
    @Mock
    private DailyScheduleMapper dailyScheduleMapper;

    /**
     * Заглушка для DailyScheduleRepository
     */
    @Mock
    private DailyScheduleRepository dailyScheduleRepository;

    /**
     * Заглушка для SchoolClassRepository
     */
    @Mock
    private SchoolClassRepository classRepository;

    /**
     * Заглушка для SubjectRepository
     */
    @Mock
    private SubjectRepository subjectRepository;

    /**
     * Внедрение заглушек и создание экземпляра DailyScheduleService
     */
    @InjectMocks
    private DailyScheduleService dailyScheduleService;

    /**
     * Инициализация моков перед выполнением каждого теста
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирует создание нового расписания.
     * Проверяет, что метод создает новое расписание и сохраняет его в репозитории.
     */
    @Test
    public void testCreateSchedule() {
        Long classId = 1L;
        RequestDailyScheduleDto requestDto = new RequestDailyScheduleDto(LocalDate.now(), "Monday",
                List.of(new RequestLessonDto(1, "Math", "Math")));


        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(classId);

        when(classRepository.findById(classId)).thenReturn(Optional.of(schoolClass));
        when(subjectRepository.findByName("Math")).thenReturn(null);

        dailyScheduleService.createSchedule(requestDto, classId);

        verify(dailyScheduleRepository).save(any(DailySchedule.class));
    }

    /**
     * Тестирует создание расписания, когда класс не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testCreateSchedule_ClassNotFound() {
        Long classId = 1L;
        RequestDailyScheduleDto requestDto = new RequestDailyScheduleDto(LocalDate.now(), "Monday",
                List.of(new RequestLessonDto(1, "Math", "Math")));

        when(classRepository.findById(classId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            dailyScheduleService.createSchedule(requestDto, classId);
        });

        assertEquals("Class with id 1 not found", exception.getMessage());
    }
}

