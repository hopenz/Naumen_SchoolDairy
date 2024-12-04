package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.student.*;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.mapper.student.StudentMapper;
import ru.naumen.naumen_schooldairy.data.repository.StudentRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для работы с сущностью Student.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    /**
     * Экземпляр репозитория для работы с сущностью Student.
     */
    private final StudentRepository studentRepository;

    /**
     * Экземпляр маппера для преобразования сущностей Student.
     */
    private final StudentMapper studentMapper;

    /**
     * Получает список всех школьников.
     *
     * @return список DTO ResponseStudentDto, представляющих всех студентов.
     */
    @Transactional
    public List<ResponseStudentWithClassDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    /**
     * Обновляет информацию о школьнике по его идентификатору.
     *
     * @param id                идентификатор студента, которого необходимо обновить.
     * @param requestStudentDto DTO с новыми данными студента.
     * @return объект ResponseStudentDto, представляющий обновленного студента.
     * @throws EntityNotFoundException если студент с указанным идентификатором не найден.
     */
    @Transactional
    public ResponseStudentWithClassDto updateStudent(Long id, RequestStudentDto requestStudentDto) {
        Student studentDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        studentDb.setName(requestStudentDto.name());
        studentDb.setSurname(requestStudentDto.surname());
        studentDb.setPatronymic(requestStudentDto.patronymic());
        studentDb.setDateOfBirth(requestStudentDto.dateOfBirth());
        studentDb.setParentContact(requestStudentDto.parentContact());
        studentDb.setPhoneNumber(requestStudentDto.phoneNumber());

        return studentMapper.toResponseDto(studentRepository.save(studentDb));
    }

    /**
     * Получает школьника по его идентификатору.
     *
     * @param id идентификатор студента, которого необходимо получить.
     * @return объект ResponseStudentDto, представляющий найденного студента.
     * @throws EntityNotFoundException если студент с указанным идентификатором не найден.
     */
    @Transactional
    public ResponseStudentWithClassDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        return studentMapper.toResponseDto(student);
    }

    /**
     * Создает нового школьника.
     *
     * @param requestStudentDto DTO с данными нового студента.
     * @return объект ResponseStudentDto, представляющий созданного студента.
     */
    @Transactional
    public ResponseStudentWithClassDto createStudent(RequestStudentDto requestStudentDto) {
        Student student = studentMapper.toEntity(requestStudentDto);
        Student studentDb = studentRepository.save(student);
        return studentMapper.toResponseDto(studentDb);
    }

    /**
     * Получает школьника по его идентификатору и дате.
     *
     * @param studentId идентификатор студента, которого необходимо получить.
     * @param dateDay   дата, для которой необходимо получить расписание студента.
     * @return объект ResponseStudentWithScheduleDto, представляющий студента с расписанием на указанную дату.
     */
    @Transactional
    public ResponseStudentWithScheduleDto getStudentByIdAndDate(Long studentId, LocalDate dateDay) {
        Student studentDb = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student", studentId));
        SchoolClass schoolClass = studentDb.getSchoolClass();
        Set<DailySchedule> dailySchedules = schoolClass.getDailySchedules();
        schoolClass.setDailySchedules(dailySchedules.stream().filter(schedule -> schedule.getDateDay().equals(dateDay)).collect(Collectors.toSet()));
        return studentMapper.toResponseWithSchedule(studentDb);
    }

    /**
     * Получает предметы и оценки школьника по его идентификатору.
     *
     * @param id идентификатор студента, для которого необходимо получить предметы и оценки.
     * @return объект ResponseStudentWithSubjectsAndMarksDto, представляющий предметы и оценки студента.
     * @throws EntityNotFoundException если студент с указанным идентификатором не найден.
     */
    @Transactional
    public ResponseStudentWithSubjectsAndMarksDto getSubjectsAndMarks(Long id) {
        Student studentDb = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toResponseWithSubjectsAndMarks(studentDb);
    }

    /**
     * Получает оценки студентов для указанного класса и предмета.
     *
     * @param classId   идентификатор класса, для которого нужно получить оценки студентов.
     * @param subjectId идентификатор предмета, по которому нужно получить оценки.
     * @return список DTO с информацией о студентах и их оценках по указанному предмету.
     */
    @Transactional
    public List<ResponseStudentWithMarksDto> getStudentsMarks(Long classId, Long subjectId) {
        List<Student> students = studentRepository.findAllBySchoolClass_Id(classId);
        for (Student student : students) {
            student.setMarks(
                    student.getMarks().stream()
                            .filter(mark -> mark.getSubject().getId().equals(subjectId))
                            .collect(Collectors.toSet())
            );
        }
        return students.stream().map(studentMapper::toResponseWithMarks).toList();
    }
}
