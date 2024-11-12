package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;

import java.time.LocalDate;
import java.util.List;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    DailySchedule findDailyScheduleByDateDayAndClassField_Id(LocalDate dateDay, Long classField_id);
}