package ru.naumen.naumen_schooldairy.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Long id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @OneToMany(mappedBy = "subject")
    private Set<Homework> homework = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subject")
    private Set<Lesson> lessons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subject")
    private Set<Mark> marks = new LinkedHashSet<>();

}