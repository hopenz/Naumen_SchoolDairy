package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Mark;

import java.time.LocalDate;

/**
 * Репозиторий для работы с сущностью Mark.
 * Предоставляет методы для доступа к данным оценок.
 */
public interface MarkRepository extends JpaRepository<Mark, Long> {
    Mark findMarkByStudent_IdAndSubject_IdAndGradeDate(Long studentId, Long subjectId, LocalDate date);
}