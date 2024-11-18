package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность школьного класса
 */
@Getter
@Setter
@Entity
@Table(name = "school_class")
public class SchoolClass {

    /**
     * Идентификатор школьного класса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Long id;

    /**
     * Название класса
     */
    @Column(name = "class_name", length = Integer.MAX_VALUE)
    private String className;

    /**
     * Классный руководитель
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /**
     * Расписание
     */
    @OneToMany(mappedBy = "classField")
    private Set<DailySchedule> dailySchedules = new LinkedHashSet<>();

    /**
     * Перечень учащихся
     */
    @OneToMany(mappedBy = "schoolClass")
    private Set<Student> students = new LinkedHashSet<>();

}