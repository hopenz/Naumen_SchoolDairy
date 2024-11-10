package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
}