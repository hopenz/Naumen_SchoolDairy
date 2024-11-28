package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность для представления занятий
 */
@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {

    /**
     * Идентификатор урока
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    private Long id;

    /**
     * Предмет
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    /**
     * Расписание
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_schedule_id")
    private DailySchedule dailySchedule;

    /**
     * Порядок в расписании
     */
    @Column(name = "lesson_number")
    private Integer lessonNumber;

    /**
     * Перечень домашних заданий
     */
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Homework> homework = new LinkedHashSet<>();

    /**
     * Перечень полученных оценок
     */
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Mark> marks = new LinkedHashSet<>();

}