package ru.naumen.naumen_schooldairy.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.student.*;
import ru.naumen.naumen_schooldairy.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления операциями, связанными со школьниками.
 */
@RestController
@RequestMapping("/api/students")
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
    public ResponseEntity<List<ResponseStudentWithClassDto>> getAllStudents() {
        List<ResponseStudentWithClassDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Возвращает конкретного школьника
     *
     * @param id идентификатор школьника
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий школьника
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStudentWithClassDto> getStudentById(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseStudentWithClassDto responseStudentWithClassDto = studentService.getStudentById(id);
        return ResponseEntity.ok(responseStudentWithClassDto);
    }

    /**
     * Обновляет информацию о существующем школьнике
     *
     * @param id                идентификатор школьника
     * @param requestStudentDto объект RequestStudentDto, содержащий обновленную информацию
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий обновленного школьника
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStudentWithClassDto> updateStudent(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentWithClassDto updateStudent = studentService.updateStudent(id, requestStudentDto);
        return ResponseEntity.ok(updateStudent);
    }

    /**
     * Создает нового школьника
     *
     * @param requestStudentDto объект RequestStudentDto, содержащий информацию о новом школьнике
     * @return ResponseEntity, содержащий объект ResponseStudentDto, представляющий созданного школьника
     */
    @PostMapping
    public ResponseEntity<ResponseStudentWithClassDto> createStudent(
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentWithClassDto createdStudent = studentService.createStudent(requestStudentDto);
        return ResponseEntity.ok(createdStudent);
    }

    /**
     * Возвращает расписание школьника по его ID и конкретной дате
     *
     * @param id   идентификатор школьника
     * @param date дата
     * @return ResponseEntity, содержащий объект ResponseStudentWithScheduleDto
     */
    @GetMapping("/lessons")
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
    @GetMapping("/marks/{id}")
    public ResponseEntity<ResponseStudentWithSubjectsAndMarksDto> getSubjectsAndMarks(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseStudentWithSubjectsAndMarksDto studentSubjectMark = studentService.getSubjectsAndMarks(id);
        return ResponseEntity.ok(studentSubjectMark);
    }

    @GetMapping("/marks")
    public ResponseEntity<List<ResponseStudentWithMarksDto>> getStudentsMarks
            (@Parameter(name = "classId") @NotNull @Positive @RequestParam("classId") Long classId,
             @Parameter(name = "subjectId") @NotNull @Positive @RequestParam("subjectId") Long subjectId) {
        return ResponseEntity.ok(studentService.getStudentsMarks(classId, subjectId));
    }
}
