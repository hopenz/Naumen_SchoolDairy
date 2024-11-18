package ru.naumen.naumen_schooldairy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;

import java.time.LocalDate;

/**
 * Репозиторий для работы с сущностью DailySchedule.
 * Предоставляет методы для доступа к данным расписания на день
 */
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    /**
     * Находит расписание на день по указанной дате и идентификатору класса.
     *
     * @param dateDay      дата, для которой необходимо найти расписание.
     * @param classFieldId уникальный идентификатор класса, к которому относится расписание.
     * @return объект DailySchedule, представляющий расписание на указанный день и класс,
     * или null, если расписание не найдено.
     */
    DailySchedule findDailyScheduleByDateDayAndClassField_Id(LocalDate dateDay, Long classFieldId);
}