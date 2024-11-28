package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;

/**
 * Репозиторий для работы с сущностью Lesson.
 * Предоставляет методы для доступа к данным уроков
 */
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson findLessonByDailySchedule_IdAndSubject_Id(Long dailyScheduleId, Long subjectId);
}