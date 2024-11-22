package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность для представления учителя
 */
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher {

    /**
     * Идентификатор учителя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    private Long id;

    /**
     * Имя учителя
     */
    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    /**
     * Фамилия учителя
     */
    @Column(name = "surname", length = Integer.MAX_VALUE)
    private String surname;

    /**
     * Отчество учителя
     */
    @Column(name = "patronymic", length = Integer.MAX_VALUE)
    private String patronymic;

    /**
     * Номер телефона учителя
     */
    @Column(name = "phone_number", length = Integer.MAX_VALUE)
    private String phoneNumber;

    /**
     * Перечень классов (1 класс - 1 учитель)
     */
    @OneToMany(mappedBy = "teacher")
    private Set<SchoolClass> schoolClasses = new LinkedHashSet<>();

}