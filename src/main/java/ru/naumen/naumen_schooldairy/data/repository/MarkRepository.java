package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Mark;

import java.time.LocalDate;

/**
 * Репозиторий для работы с сущностью Mark.
 * Предоставляет методы для доступа к данным оценок.
 */
public interface MarkRepository extends JpaRepository<Mark, Long> {

    /**
     * Находит оценку по идентификатору ученика, идентификатору предмета и дате выставления оценки.
     *
     * @param studentId идентификатор студента, чью оценку необходимо найти
     * @param subjectId идентификатор предмета, к которому относится оценка
     * @param date      дата, когда была выставлена оценка
     * @return объект Mark, соответствующий заданным идентификаторам и дате, или null, если оценка не найдена
     */
    Mark findMarkByStudent_IdAndSubject_IdAndGradeDate(Long studentId, Long subjectId, LocalDate date);
}