package ru.naumen.naumen_schooldairy.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    /**
     * Сервис для работы с учителями
     */
    private final TeacherService teacherService;

    /**
     * Получение информации об учителе по его идентификатору.
     *
     * @param id идентификатор учителя
     * @return ResponseEntity с информацией о запрашиваемом учителе
     */
    @Operation(summary = "Информация об учителе", description = "Получение информации об учителе по его идентификатору.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTeacherDto> getTeacherById(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseTeacherDto responseTeacherDto = teacherService.getTeacherById(id);
        return ResponseEntity.ok(responseTeacherDto);
    }

    /**
     * Обновление информации об учителе по его идентификатору.
     *
     * @param id                идентификатор учителя
     * @param requestTeacherDto объект запроса с новыми данными учителя
     * @return ResponseEntity с обновленной информацией об учителе
     */
    @Operation(summary = "Обновление информации об учителе", description = "Обновление информации об учителе по его идентификатору.")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTeacherDto> updateTeacher(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestBody @Validated RequestTeacherDto requestTeacherDto) {
        ResponseTeacherDto responseTeacherDto = teacherService.updateTeacher(id, requestTeacherDto);
        return ResponseEntity.ok(responseTeacherDto);
    }

    /**
     * Получение списка учеников, связанных с учителем по его идентификатору.
     *
     * @param id идентификатор учителя
     * @return ResponseEntity с информацией об учениках данного учителя
     */
    @Operation(summary = "Получение списка учеников, связанных с учителем",
            description = "Получение списка учеников, связанных с учителем по его идентификатору.")
    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseTeacherWithStudentsDto> getStudentsByTeacherId
    (@NotNull @Positive @PathVariable("id") Long id) {
        ResponseTeacherWithStudentsDto responseTeacherWithStudentsDto = teacherService.getStudentsByTeacherId(id);
        return ResponseEntity.ok(responseTeacherWithStudentsDto);
    }

    /**
     * Добавление ученика к списку учеников учителя.
     *
     * @param id    идентификатор учителя
     * @param email электронная почта ученика, которого необходимо добавить
     * @return ResponseEntity без содержимого (204 No Content), если добавление прошло успешно
     */
    @Operation(summary = "Добавление ученика к списку учеников", description = "Добавление ученика к списку учеников учителя.")
    @PostMapping("/students/{id}")
    public ResponseEntity<Void> addStudentToTeacher(
            @NotNull @Positive @PathVariable("id") Long id,
            @RequestParam("email") @NotNull @Email String email) {
        teacherService.addStudentToTeacher(id, email);
        return ResponseEntity.ok().build();
    }
}
