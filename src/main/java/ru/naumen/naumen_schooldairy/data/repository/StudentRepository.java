package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.naumen.naumen_schooldairy.data.entity.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с сущностью Student.
 * Предоставляет методы для доступа к данным студентов.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Находит студента по его идентификатору и дате.
     *
     * @param studentId идентификатор студента, которого необходимо найти.
     * @param dateDay   дата, на которую необходимо найти расписание студента.
     * @return объект Student, представляющий найденного студента,
     * или null, если студент не найден.
     */
    @Query("SELECT s FROM Student s JOIN s.schoolClass sc JOIN DailySchedule ds ON sc.id = ds.id "
            + "WHERE s.id = :studentId AND ds.dateDay = :dateDay")
    Student findStudentByIdAndDate(@Param("studentId") Long studentId,
                                   @Param("dateDay") LocalDate dateDay);

    List<Student> findAllBySchoolClass_Id(Long classId);


}