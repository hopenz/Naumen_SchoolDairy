package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithClassDto;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;
import ru.naumen.naumen_schooldairy.data.repository.StudentRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса для работы со школьниками.
 */
public class StudentServiceTest {

    /**
     * Заглушка репозитория учеников
     */
    @Mock
    private StudentRepository studentRepository;

    /**
     * Заглушка маппера учеников
     */
    @Mock
    private StudentMapper studentMapper;

    /**
     * Внедрение заглушек и создание экземпляра сервиса
     */
    @InjectMocks
    private StudentService studentService;

    /**
     * Инициализация моков перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирует получение всех студентов.
     * Проверяет, что метод возвращает корректный список DTO.
     */
    @Test
    public void testGetAllStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> students = List.of(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toResponseDto(student1)).thenReturn(new ResponseStudentWithClassDto(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null, null));
        when(studentMapper.toResponseDto(student2)).thenReturn(new ResponseStudentWithClassDto(2L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null, null));

        List<ResponseStudentWithClassDto> result = studentService.getAllStudents();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentRepository).findAll();
    }

    /**
     * Тестирует обновление информации о студенте.
     * Проверяет, что метод обновляет данные и возвращает корректный DTO.
     */
    @Test
    public void testUpdateStudent_Success() {
        Long studentId = 1L;
        RequestStudentDto requestDto = new RequestStudentDto("Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null);

        Student newStudent = new Student();
        newStudent.setId(studentId);

        Student someStudent = new Student();

        when(studentMapper.toEntity(requestDto)).thenReturn(newStudent);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(newStudent));
        when(studentRepository.save(newStudent)).thenReturn(someStudent);

        ResponseStudentWithClassDto responseDto = new ResponseStudentWithClassDto(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null, null);

        when(studentMapper.toResponseDto(someStudent)).thenReturn(responseDto);

        ResponseStudentWithClassDto result = studentService.updateStudent(studentId, requestDto);

        assertEquals(responseDto, result);
        verify(studentRepository).save(newStudent);
    }

    /**
     * Тестирует обновление информации о студенте, когда студент не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testUpdateStudent_StudentNotFound() {
        Long studentId = 1L;
        RequestStudentDto requestDto = new RequestStudentDto("Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null);

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            studentService.updateStudent(studentId, requestDto);
        });

        assertEquals("Student with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует получение студента по его идентификатору.
     * Проверяет, что метод возвращает корректный DTO.
     */
    @Test
    public void testGetStudentById_Success() {
        Long studentId = 1L;

        Student student = new Student();
        student.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        ResponseStudentWithClassDto responseDto = new ResponseStudentWithClassDto(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null, null);
        when(studentMapper.toResponseDto(any(Student.class))).thenReturn(responseDto);

        ResponseStudentWithClassDto result = studentService.getStudentById(studentId);

        assertNotNull(result);
        verify(studentRepository).findById(studentId);
    }

    /**
     * Тестирует получение студента по его идентификатору, когда студент не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testGetStudentById_StudentNotFound() {
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudentById(studentId);
        });

        assertEquals("Student with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует создание нового студента.
     * Проверяет, что метод создает нового студента и возвращает корректный DTO.
     */
    @Test
    public void testCreateStudent() {
        RequestStudentDto requestDto = new RequestStudentDto("Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null);

        Student studentToSave = new Student();
        when(studentMapper.toEntity(requestDto)).thenReturn(studentToSave);

        ResponseStudentWithClassDto responseDto = new ResponseStudentWithClassDto(1L, "Иван", "Иванов", "Иванович",
                LocalDate.of(2000, 1, 1), null, null, null);
        when(studentMapper.toResponseDto(any())).thenReturn(responseDto);

        Student studentDb = new Student();
        when(studentRepository.save(any())).thenReturn(studentDb);

        ResponseStudentWithClassDto result = studentService.createStudent(requestDto);

        assertEquals(responseDto, result);
        verify(studentRepository).save(studentToSave);
    }
}
