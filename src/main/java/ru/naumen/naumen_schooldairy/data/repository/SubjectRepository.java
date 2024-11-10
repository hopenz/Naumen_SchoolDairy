package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}