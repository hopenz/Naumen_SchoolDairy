package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.data.entity.*;
import ru.naumen.naumen_schooldairy.data.repository.*;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;

import java.time.LocalDate;

/**
 * Сервис для управления оценками студентов
 */
@Service
@RequiredArgsConstructor
public class MarkService {

    /**
     * Репозиторий для работы с оценками
     */
    private final MarkRepository markRepository;

    /**
     * Репозиторий для работы с уроками
     */
    private final LessonRepository lessonRepository;

    /**
     * Репозиторий для работы с расписанием
     */
    private final DailyScheduleRepository dailyScheduleRepository;

    /**
     * Репозиторий для работы со студентами
     */
    private final StudentRepository studentRepository;

    /**
     * Репозиторий для работы с предметами
     */
    private final SubjectRepository subjectRepository;

    /**
     * Обновляет оценку студента по указанному предмету на заданную дату.
     *
     * @param subjectId идентификатор предмета, по которому обновляется оценка.
     * @param studentId идентификатор студента, чья оценка обновляется.
     * @param date      дата, на которую обновляется оценка.
     * @param mark      новая оценка студента.
     */
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
