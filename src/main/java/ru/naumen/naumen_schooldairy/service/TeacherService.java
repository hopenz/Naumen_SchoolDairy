package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.teacher.RequestTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithStudentsDto;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;
import ru.naumen.naumen_schooldairy.data.mapper.teacher.TeacherMapper;
import ru.naumen.naumen_schooldairy.data.repository.TeacherRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

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
}
