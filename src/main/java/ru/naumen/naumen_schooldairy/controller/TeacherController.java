package ru.naumen.naumen_schooldairy.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.service.TeacherService;

/**
 * Контроллер для управления операциями, связанными с учителем.
 */
@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
@Validated
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * Возвращает конкретного учителя по его идентификатору.
     *
     * @param id идентификатор учителя
     * @return TODO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTeacherDto> getTeacherById(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseTeacherDto responseTeacherDto = teacherService.getTeacherById(id);
        return ResponseEntity.ok(responseTeacherDto);
    }

    /**
     * Обновляет информацию о существующем учителе
     *
     * @param id                идентификатор учителя
     * TODO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTeacherDto> updateTeacher(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestBody @Validated RequestTeacherDto requestTeacherDto) {
        ResponseTeacherDto responseTeacherDto = teacherService.updateTeacher(id, requestTeacherDto);
        return ResponseEntity.ok(responseTeacherDto);
    }

    /**
     * Возвращает список учеников в классе по идентификатору учителя.
     *
     * TODO
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseStudentInClassDto> getStudentsByTeacherId(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseStudentInClassDto responseStudentInClassDto = teacherService.getStudentsByTeacherId(id);
        return ResponseEntity.ok(responseClassDto);
    }
}
