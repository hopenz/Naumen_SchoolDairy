package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}