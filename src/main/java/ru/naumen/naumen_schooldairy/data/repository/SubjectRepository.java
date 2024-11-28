package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Subject.
 * Предоставляет методы для доступа к данным предметов.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findByName(String name);

    @Query("SELECT s FROM Subject s JOIN s.lessons sl JOIN sl.dailySchedule ds " +
            "JOIN ds.classField sc WHERE sc.id = :classId")
    List<Subject> findAllDistinctForClass(@Param("classId") Long classId);

}