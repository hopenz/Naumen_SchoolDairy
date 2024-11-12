package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithSubjectsAndMarksDto;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;
import ru.naumen.naumen_schooldairy.data.repository.StudentRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Transactional
    public List<ResponseStudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Transactional
    public ResponseStudentDto updateStudent(Long id, RequestStudentDto requestStudentDto) {
        Student studentDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        Student newStudent = studentMapper.toEntity(requestStudentDto);
        newStudent.setId(id);
        newStudent.setName(requestStudentDto.name());
        newStudent.setSurname(requestStudentDto.surname());
        newStudent.setPatronymic(requestStudentDto.patronymic());
        newStudent.setDateOfBirth(requestStudentDto.dateOfBirth());
        newStudent.setParentContact(requestStudentDto.parentContact());
        newStudent.setPhoneNumber(requestStudentDto.phoneNumber());

        return studentMapper.toResponseDto(studentRepository.save(newStudent));

    }

    @Transactional
    public ResponseStudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        return studentMapper.toResponseDto(student);
    }

    @Transactional
    public ResponseStudentDto createStudent(RequestStudentDto requestStudentDto) {
        Student student = studentMapper.toEntity(requestStudentDto);
        Student studentDb = studentRepository.save(student);
        return studentMapper.toResponseDto(studentDb);
    }

    @Transactional
    public ResponseStudentWithScheduleDto getStudentByIdAndDate(Long studentId, LocalDate dateDay) {
        Student studentDb = studentRepository.findStudentByIdAndDate(studentId, dateDay);
        return studentMapper.toResponseWithSchedule(studentDb);
    }

    @Transactional
    public ResponseStudentWithSubjectsAndMarksDto getSubjectsAndMarks(Long id) {
        Student studentDb = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toResponseWithSubjectsAndMarks(studentDb);
    }

}
