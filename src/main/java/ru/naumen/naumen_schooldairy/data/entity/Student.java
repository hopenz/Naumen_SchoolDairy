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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "surname", length = Integer.MAX_VALUE)
    private String surname;

    @Column(name = "patronymic", length = Integer.MAX_VALUE)
    private String patronymic;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "parent_contact", length = Integer.MAX_VALUE)
    private String parentContact;

    @Column(name = "phone_number", length = Integer.MAX_VALUE)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student")
    private Set<Mark> marks = new LinkedHashSet<>();

}