package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Mark;

/**
 * Репозиторий для работы с сущностью Mark.
 * Предоставляет методы для доступа к данным оценок.
 */
public interface MarkRepository extends JpaRepository<Mark, Long> {
}