package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.teacher.RequestTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithStudentsDto;
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

/**
 * TODO
 */
@Service
@RequiredArgsConstructor
public class TeacherService {

    /**
     * TODO
     */
    private final TeacherRepository teacherRepository;

    /**
     * TODO
     */
    private final TeacherMapper teacherMapper;

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;


    /**
     * Получает учителя по его идентификатору
     *
     * @param id идентификатор учителя
     * @return Объект ResponseTeacherDto00, представляющий найденного учителя
     */
    @Transactional
    public ResponseTeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        return teacherMapper.toResponseDto(teacher);
    }

    /**
     * Обновляет информацию об учителе по его идентификатору
     *
     * @param id                идентификатор учителя
     * @param requestTeacherDto DTO с новыми данными учителя
     * @return Объект ResponseTeacherDto00, представляющий обновленного учителя
     */
    @Transactional
    public ResponseTeacherDto updateTeacher(Long id, RequestTeacherDto requestTeacherDto) {
        Teacher teacherDb = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        Teacher newTeacher = teacherMapper.toEntity(requestTeacherDto);
        newTeacher.setId(id);
        newTeacher.setName(requestTeacherDto.name());
        newTeacher.setSurname(requestTeacherDto.surname());
        newTeacher.setPatronymic(requestTeacherDto.patronymic());
        newTeacher.setPhoneNumber(requestTeacherDto.phoneNumber());

        return teacherMapper.toResponseDto(teacherRepository.save(newTeacher));
    }

    /**
     * TODO
     *
     * @param id
     * @return
     */
    @Transactional
    public ResponseTeacherWithStudentsDto getStudentsByTeacherId(Long id) {
        Teacher teacherDb = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        return teacherMapper.toDto(teacherDb);
    }

    @Transactional
    public void addStudentToTeacher(Long id, String email) {
        Teacher teacherDb = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User with email" + email + "not found"));
        if (!user.getRole().equals(Role.ROLE_STUDENT) || user.getStudent().getSchoolClass() != null) {
            throw new IllegalArgumentException("User with email " + email + " is not a student or already has a class");
        }

        Student student = user.getStudent();
        student.setSchoolClass(teacherDb.getSchoolClasses().stream().findFirst().orElseThrow(
                () -> new ResourceNotFoundException("School class not found")));

        studentRepository.save(student);
    }
}
