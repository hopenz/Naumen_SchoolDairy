package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Сущность для представления оценки
 */
@Getter
@Setter
@Entity
@Table(name = "mark")
public class Mark {

    /**
     * Идентификатор оценки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id", nullable = false)
    private Long id;

    /**
     * Школьник
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Предмет
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    /**
     * Урок
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    /**
     * Оценка
     */
    @Column(name = "mark")
    private Integer mark;

    /**
     * Дата получения
     */
    @Column(name = "grade_date")
    private LocalDate gradeDate;

}