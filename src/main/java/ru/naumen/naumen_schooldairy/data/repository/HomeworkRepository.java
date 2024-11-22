package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Homework;

/**
 * Репозиторий для работы с сущностью Homework.
 * Предоставляет методы для доступа к данным домашних заданий.
 */
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}