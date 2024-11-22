package ru.naumen.naumen_schooldairy.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentWithSubjectsAndMarksDto;
import ru.naumen.naumen_schooldairy.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления операциями, связанными со школьниками.
 */
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

    /**
     * Экземпляр сервиса для управления школьниками.
     */
    private final StudentService studentService;

    /**
     * Возвращает список всех школьников
     *
     * @return ResponseEntity, содержащий список объектов ResponseStudentDto, представляющих школьников
     */
    @GetMapping
    public ResponseEntity<List<ResponseStudentDto>> getAllStudents() {
        List<ResponseStudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Возвращает конкретного школьника
     *
     * @param id идентификатор школьника
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий школьника
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStudentDto> getStudentById(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseStudentDto responseStudentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(responseStudentDto);
    }

    /**
     * Обновляет информацию о существующем школьнике
     *
     * @param id                идентификатор школьника
     * @param requestStudentDto объект RequestStudentDto, содержащий обновленную информацию
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий обновленного школьника
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStudentDto> updateStudent(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentDto updateStudent = studentService.updateStudent(id, requestStudentDto);
        return ResponseEntity.ok(updateStudent);
    }

    /**
     * Создает нового школьника
     *
     * @param requestStudentDto объект RequestStudentDto, содержащий информацию о новом школьнике
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий созданного школьника
     */
    @PostMapping
    public ResponseEntity<ResponseStudentDto> createStudent(
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentDto createdStudent = studentService.createStudent(requestStudentDto);
        return ResponseEntity.ok(createdStudent);
    }

    /**
     * Возвращает расписание школьника по его ID и конкретной дате
     *
     * @param id   идентификатор школьника
     * @param date дата
     * @return ResponseEntity, содержащий объект ResponseStudentWithScheduleDto
     */
    @GetMapping("/lesson")
    public ResponseEntity<ResponseStudentWithScheduleDto> getStudentByIdAndDate(
            @NotNull @Positive @RequestParam("id") Long id,
            @NotNull @RequestParam("date") LocalDate date) {
        ResponseStudentWithScheduleDto studentSchedule = studentService.getStudentByIdAndDate(id, date);
        return ResponseEntity.ok(studentSchedule);
    }

    /**
     * Получает предметы и оценки школьника по его ID
     *
     * @param id идентификатор школьника
     * @return ResponseEntity, содержащий объект ResponseStudentWithSubjectsAndMarksDto
     */
    @GetMapping("/subjectsAndMarks")
    public ResponseEntity<ResponseStudentWithSubjectsAndMarksDto> getSubjectsAndMarks(
            @NotNull @Positive @RequestParam("id") Long id) {
        ResponseStudentWithSubjectsAndMarksDto studentSubjectMark = studentService.getSubjectsAndMarks(id);
        return ResponseEntity.ok(studentSubjectMark);
    }

}
