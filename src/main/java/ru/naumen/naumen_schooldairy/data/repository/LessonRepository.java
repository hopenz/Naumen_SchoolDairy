package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;

/**
 * Репозиторий для работы с сущностью Lesson.
 * Предоставляет методы для доступа к данным уроков
 */
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    /**
     * Находит урок по идентификатору ежедневного расписания и идентификатору предмета.
     *
     * @param dailyScheduleId идентификатор ежедневного расписания, к которому относится урок
     * @param subjectId       идентификатор предмета, к которому относится урок
     * @return объект Lesson, соответствующий заданным идентификаторам, или null, если урок не найден
     */
    Lesson findLessonByDailySchedule_IdAndSubject_Id(Long dailyScheduleId, Long subjectId);
}