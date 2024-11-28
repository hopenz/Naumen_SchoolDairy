package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.data.entity.*;
import ru.naumen.naumen_schooldairy.data.repository.*;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;

    private final LessonRepository lessonRepository;

    private final DailyScheduleRepository dailyScheduleRepository;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public void updateMark(Long subjectId, Long studentId, LocalDate date, Integer mark) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new EntityNotFoundException("Student", studentId));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                () -> new EntityNotFoundException("Subject", subjectId));
        DailySchedule dailySchedule = dailyScheduleRepository.
                findDailyScheduleByDateDayAndClassField_Id(date, student.getSchoolClass().getId());
        Lesson lesson = lessonRepository.findLessonByDailySchedule_IdAndSubject_Id(dailySchedule.getId(), subjectId);

        Mark oldMark = markRepository.findMarkByStudent_IdAndSubject_IdAndGradeDate(studentId, subjectId, date);
        markRepository.delete(oldMark);

        Mark newMark = new Mark();
        newMark.setMark(mark);
        newMark.setSubject(subject);
        newMark.setStudent(student);
        newMark.setGradeDate(date);
        newMark.setLesson(lesson);

        markRepository.save(newMark);
    }
}
