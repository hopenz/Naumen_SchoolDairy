package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

/**
 * Репозиторий для работы с сущностью Subject.
 * Предоставляет методы для доступа к данным предметов.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}