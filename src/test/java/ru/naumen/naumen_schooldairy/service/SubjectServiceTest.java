package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Subject;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;
import ru.naumen.naumen_schooldairy.data.repository.SubjectRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * Тестирование сервиса для работы с предметами.
 */
public class SubjectServiceTest {

    /**
     * Заглушка репозитория предметов
     */
    @Mock
    private SubjectRepository subjectRepository;

    /**
     * Заглушка маппера предметов
     */
    @Mock
    private SubjectMapper subjectMapper;

    /**
     * Внедрение заглушек и создание экземпляра сервиса
     */
    @InjectMocks
    private SubjectService subjectService;

    /**
     * Инициализация моков перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирует получение списка уникальных предметов для указанного класса.
     * Проверяет, что метод возвращает корректный список DTO.
     */
    @Test
    public void testGetSubjectsForClass() {
        Long classId = 1L;

        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        List<Subject> subjects = List.of(subject1, subject2);

        when(subjectRepository.findAllDistinctForClass(classId)).thenReturn(subjects);
        when(subjectMapper.toResponseDto(subject1)).thenReturn(new ResponseSubjectDto(1L, "Предмет 1"));
        when(subjectMapper.toResponseDto(subject2)).thenReturn(new ResponseSubjectDto(2L, "Предмет 2"));

        List<ResponseSubjectDto> result = subjectService.getSubjectsForClass(classId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(subjectRepository).findAllDistinctForClass(classId);
        verify(subjectMapper, times(2)).toResponseDto(any(Subject.class));
    }
}
