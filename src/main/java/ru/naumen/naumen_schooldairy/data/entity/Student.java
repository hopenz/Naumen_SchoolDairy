package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Сущность для представления школьника
 */
@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * Идентификатор школьника
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    /**
     * Имя школьника
     */
    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    /**
     * Фамилия школьника
     */
    @Column(name = "surname", length = Integer.MAX_VALUE)
    private String surname;

    /**
     * Отчество школьника
     */
    @Column(name = "patronymic", length = Integer.MAX_VALUE)
    private String patronymic;

    /**
     * Дата рождения школьника
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Контакт родителя
     */
    @Column(name = "parent_contact", length = Integer.MAX_VALUE)
    private String parentContact;

    /**
     * Номер телефона школьника
     */
    @Column(name = "phone_number", length = Integer.MAX_VALUE)
    private String phoneNumber;

    /**
     * Класс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    /**
     * Перечень оценок школьника
     */
    @OneToMany(mappedBy = "student")
    private Set<Mark> marks = new LinkedHashSet<>();

}