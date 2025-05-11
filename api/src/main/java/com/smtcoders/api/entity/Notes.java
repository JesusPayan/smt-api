package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Notes")
public class Notes {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Column(name = "student_id")
    private Long studentID;

    @ManyToOne
    @Column(name = "course_id")
    private Long courseID;

}
