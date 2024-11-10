package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_schedule_id")
    private DailySchedule dailySchedule;

    @Column(name = "lesson_number")
    private Integer lessonNumber;

    @OneToMany(mappedBy = "lesson")
    private Set<Homework> homework = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<Mark> marks = new LinkedHashSet<>();

}