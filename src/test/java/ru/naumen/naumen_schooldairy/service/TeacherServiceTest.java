package ru.naumen.naumen_schooldairy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.naumen.naumen_schooldairy.data.dto.teacher.RequestTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithStudentsDto;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;
import ru.naumen.naumen_schooldairy.data.mapper.teacher.TeacherMapper;
import ru.naumen.naumen_schooldairy.data.repository.StudentRepository;
import ru.naumen.naumen_schooldairy.data.repository.TeacherRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;
import ru.naumen.naumen_schooldairy.exception.ResourceNotFoundException;
import ru.naumen.naumen_schooldairy.security.entity.Role;
import ru.naumen.naumen_schooldairy.security.entity.User;
import ru.naumen.naumen_schooldairy.security.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирует сервис для работы с учителями.
 */
public class TeacherServiceTest {

    /**
     * Заглушка для репозитория учителей.
     */
    @Mock
    private TeacherRepository teacherRepository;

    /**
     * Заглушка для маппера учителей.
     */
    @Mock
    private TeacherMapper teacherMapper;

    /**
     * Заглушка для репозитория студентов.
     */
    @Mock
    private StudentRepository studentRepository;

    /**
     * Заглушка для репозитория пользователей.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Внедрение заглушек и создание экземпляра сервиса
     */
    @InjectMocks
    private TeacherService teacherService;

    /**
     * Инициализация моков перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирует получение учителя по его идентификатору.
     * Проверяет, что метод возвращает корректный DTO.
     */
    @Test
    public void testGetTeacherById_Success() {
        Long teacherId = 1L;

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toResponseDto(any(Teacher.class))).thenReturn(new ResponseTeacherDto(1L, "Имя",
                "Фамилия", "Отчество", null, null));

        ResponseTeacherDto result = teacherService.getTeacherById(teacherId);

        assertNotNull(result);
        verify(teacherRepository).findById(teacherId);
    }

    /**
     * Тестирует получение учителя по его идентификатору, когда учитель не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testGetTeacherById_TeacherNotFound() {
        Long teacherId = 1L;

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.getTeacherById(teacherId);
        });

        assertEquals("Teacher with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует обновление информации об учителе.
     * Проверяет, что метод обновляет данные и возвращает корректный DTO.
     */
    @Test
    public void testUpdateTeacher_Success() {
        Long teacherId = 1L;
        RequestTeacherDto requestDto = new RequestTeacherDto("имя", "фамилия",
                "отчество", null);

        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacherId);

        Teacher someTeacher = new Teacher();

        when(teacherMapper.toEntity(requestDto)).thenReturn(newTeacher);
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(newTeacher));
        when(teacherRepository.save(newTeacher)).thenReturn(someTeacher);

        ResponseTeacherDto responseDto = new ResponseTeacherDto(1L, "Имя",
                "Фамилия", "Отчество", null, null);

        when(teacherMapper.toResponseDto(someTeacher)).thenReturn(responseDto);

        ResponseTeacherDto result = teacherService.updateTeacher(teacherId, requestDto);

        assertEquals(responseDto, result);
        verify(teacherRepository).save(newTeacher);
    }

    /**
     * Тестирует обновление информации об учителе, когда учитель не найден.
     * Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testUpdateTeacher_TeacherNotFound() {
        Long teacherId = 1L;
        RequestTeacherDto requestDto = new RequestTeacherDto("имя", "фамилия",
                "отчество", null);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.updateTeacher(teacherId, requestDto);
        });

        assertEquals("Teacher with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует получение студентов, связанных с указанным учителем.
     * Проверяет, что метод возвращает корректный DTO.
     */
    @Test
    public void testGetStudentsByTeacherId_Success() {
        Long teacherId = 1L;

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        ResponseTeacherWithStudentsDto responseDto = new ResponseTeacherWithStudentsDto(1L, "Имя",
                "Фамилия", "Отчество", null, null);
        when(teacherMapper.toDto(any(Teacher.class))).thenReturn(responseDto);

        ResponseTeacherWithStudentsDto result = teacherService.getStudentsByTeacherId(teacherId);

        assertNotNull(result);
        verify(teacherRepository).findById(teacherId);
    }

    /**
     * Тестирует получение студентов, связанных с указанным учителем,
     * когда учитель не найден. Ожидает выброс исключения EntityNotFoundException.
     */
    @Test
    public void testGetStudentsByTeacherId_TeacherNotFound() {
        Long teacherId = 1L;

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.getStudentsByTeacherId(teacherId);
        });

        assertEquals("Teacher with id 1 not found", exception.getMessage());
    }

    /**
     * Тестирует добавление ученика к указанному учителю.
     * Проверяет успешное добавление ученика и сохранение данных.
     */
    @Test
    public void testAddStudentToTeacher_Success() {
        Long teacherId = 1L;
        String email = "student@example.com";

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(1L);
        teacher.setSchoolClasses(Set.of(schoolClass));

        User user = new User();
        user.setEmail(email);
        user.setRole(Role.ROLE_STUDENT);

        Student student = new Student();
        student.setUser(user);
        user.setStudent(student);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        teacherService.addStudentToTeacher(teacherId, email);

        verify(studentRepository).save(student);
    }

    /**
     * Тестирует добавление ученика к указанному учителю,
     * когда ученик не найден. Ожидает выброс исключения ResourceNotFoundException.
     */
    @Test
    public void testAddStudentToTeacher_UserNotFound() {
        Long teacherId = 1L;
        String email = "student@example.com";

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.addStudentToTeacher(teacherId, email);
        });

        assertEquals("User with email" + email + "not found", exception.getMessage());
    }
}
