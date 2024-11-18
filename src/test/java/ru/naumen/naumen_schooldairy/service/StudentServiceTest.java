package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;
import ru.naumen.naumen_schooldairy.data.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса для работы с сущностью Student.
 */
public class StudentServiceTest {

    /**
     * Mock для репозитория Student.
     */
    @Mock
    private StudentRepository studentRepository;

    /**
     * Mock для маппера Student.
     */
    @Mock
    private StudentMapper studentMapper;

    /**
     * Экземпляр класса {@link StudentService}, который будет тестироваться.
     */
    @InjectMocks
    private StudentService studentService;

    /**
     * Экземпляр сущности Student.
     */
    private Student student;

    /**
     * Экземпляр DTO RequestStudentDto.
     */
    private RequestStudentDto requestStudentDto;

    /**
     * Экземпляр DTO ResponseStudentDto.
     */
    private ResponseStudentDto responseStudentDto;

    /**
     * Метод, который выполняется перед каждым тестом.
     * Инициализирует моки и создает тестовые объекты.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2005, 1, 1), "1234567890", "0987654321",
                null, null);

        requestStudentDto = new RequestStudentDto("Иван", "Иванов", "Иванович",
                LocalDate.of(2005, 1, 1), "1234567890", "0987654321");

        responseStudentDto = new ResponseStudentDto(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2005, 1, 1), "1234567890",
                "0987654321", null);
    }

    /**
     * Тестирует метод {@link StudentService#updateStudent(Long, RequestStudentDto)}.
     * Проверяет корректное обновление информации о студенте.
     */
    @Test
    public void testUpdateStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toEntity(requestStudentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toResponseDto(student)).thenReturn(responseStudentDto);

        ResponseStudentDto result = studentService.updateStudent(1L, requestStudentDto);

        assertEquals(responseStudentDto, result);
        verify(studentRepository).findById(1L);
        verify(studentRepository).save(student);
    }

    /**
     * Тестирует метод {@link StudentService#createStudent(RequestStudentDto)}.
     * Проверяет корректное создание нового студента и возврат его DTO.
     */
    @Test
    public void testCreateStudent() {
        when(studentMapper.toEntity(requestStudentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toResponseDto(student)).thenReturn(responseStudentDto);

        ResponseStudentDto result = studentService.createStudent(requestStudentDto);

        assertEquals(responseStudentDto, result);
        verify(studentMapper).toEntity(requestStudentDto);
        verify(studentRepository).save(student);
    }
}
