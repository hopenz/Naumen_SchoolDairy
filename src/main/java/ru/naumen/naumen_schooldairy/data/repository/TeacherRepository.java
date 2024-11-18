package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;

/**
 * Репозиторий для работы с сущностью Teacher.
 * Предоставляет методы для доступа к данным учителей.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}