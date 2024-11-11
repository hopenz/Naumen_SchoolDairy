package ru.naumen.naumen_schooldairy.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.naumen.naumen_schooldairy.data.dto.student.RequestStudentDto;
import ru.naumen.naumen_schooldairy.data.dto.student.ResponseStudentDto;
import ru.naumen.naumen_schooldairy.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<ResponseStudentDto>> getAllStudents() {
        List<ResponseStudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStudentDto> getStudentById(
            @NotNull @Positive @PathVariable("id") Long id) {
        ResponseStudentDto responseStudentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(responseStudentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStudentDto> updateStudent(
            @NotNull @Positive @PathVariable("id")Long id,
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentDto updateStudent = studentService.updateStudent(id, requestStudentDto);
        return ResponseEntity.ok(updateStudent);
    }

    @PostMapping
    public ResponseEntity<ResponseStudentDto> createStudent(
            @RequestBody @Validated RequestStudentDto requestStudentDto) {
        ResponseStudentDto createdStudent = studentService.createStudent(requestStudentDto);
        return ResponseEntity.ok(createdStudent);
    }

}
