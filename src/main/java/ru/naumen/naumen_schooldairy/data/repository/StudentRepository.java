package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.naumen.naumen_schooldairy.data.entity.Student;

import java.time.LocalDate;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s JOIN s.schoolClass sc JOIN DailySchedule ds ON sc.id = ds.id "
            + "WHERE s.id = :studentId AND ds.dateDay = :dateDay")
    Student findStudentByIdAndDate(@Param("studentId") Long studentId,
                                   @Param("dateDay") LocalDate dateDay);

}