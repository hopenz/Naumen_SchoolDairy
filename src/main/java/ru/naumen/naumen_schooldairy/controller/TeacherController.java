package ru.naumen.naumen_schooldairy.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.teacher.RequestTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherDto;
import ru.naumen.naumen_schooldairy.data.dto.teacher.ResponseTeacherWithStudentsDto;
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
     * @param id идентификатор учителя
     *                     TODO
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
     * <p>
     * TODO
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseTeacherWithStudentsDto> getStudentsByTeacherId
    (@NotNull @Positive @PathVariable("id") Long id) {
        ResponseTeacherWithStudentsDto responseTeacherWithStudentsDto = teacherService.getStudentsByTeacherId(id);
        return ResponseEntity.ok(responseTeacherWithStudentsDto);
    }

    @PostMapping("/students/{id}")
    public ResponseEntity<Void> addStudentToTeacher(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestParam("email") @NotNull @Email String email) {
        teacherService.addStudentToTeacher(id, email);
        return ResponseEntity.ok().build();
    }
}
