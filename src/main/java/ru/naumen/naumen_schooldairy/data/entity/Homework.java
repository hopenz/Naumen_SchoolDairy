package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность для представления домашнего задания
 */
@Getter
@Setter
@Entity
@Table(name = "homework")
public class Homework {

    /**
     * Идентификатор домашнего задания
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id", nullable = false)
    private Long id;

    /**
     * Урок
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    /**
     * Предмет
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    /**
     * Описание домашнего задания
     */
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}