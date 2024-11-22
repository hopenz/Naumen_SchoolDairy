package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность для представления расписания на день
 */
@Getter
@Setter
@Entity
@Table(name = "daily_schedule")
public class DailySchedule {
    /**
     * Идентификатор расписания
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_schedule_id", nullable = false)
    private Long id;

    /**
     * Дата дня
     */
    @Column(name = "date_day")
    private LocalDate dateDay;

    /**
     * Класс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass classField;

    /**
     * День недели
     */
    @Column(name = "day_of_week", length = Integer.MAX_VALUE)
    private String dayOfWeek;

    /**
     * Перечень уроков
     */
    @OneToMany(mappedBy = "dailySchedule")
    private Set<Lesson> lessons = new LinkedHashSet<>();

}