package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "daily_schedule")
public class DailySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_schedule_id", nullable = false)
    private Long id;

    @Column(name = "date_day")
    private LocalDate dateDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass classField;

    @Column(name = "day_of_week", length = Integer.MAX_VALUE)
    private String dayOfWeek;

    @OneToMany(mappedBy = "dailySchedule")
    private Set<Lesson> lessons = new LinkedHashSet<>();

}