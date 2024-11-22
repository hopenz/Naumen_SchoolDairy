package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность для представления предмета
 */
@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {

    /**
     * Идентификатор предмета
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Long id;

    /**
     * Название предмета
     */
    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    /**
     * Перечень домашних заданий
     */
    @OneToMany(mappedBy = "subject")
    private Set<Homework> homework = new LinkedHashSet<>();

    /**
     * Перечень уроков
     */
    @OneToMany(mappedBy = "subject")
    private Set<Lesson> lessons = new LinkedHashSet<>();

    /**
     * перечень оценок
     */
    @OneToMany(mappedBy = "subject")
    private Set<Mark> marks = new LinkedHashSet<>();

}