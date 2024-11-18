package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;

/**
 * Репозиторий для работы с сущностью SchoolClass.
 * Предоставляет методы для доступа к данным школьных классов.
 */
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}