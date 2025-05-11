package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_student")
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @Column(name = "student_id")
    private Long student_id;

    @ManyToOne
    @Column(name = "course_id")
    private Long course_id;

    @Column(name = "course_note")
    private Double note;
    @Column(name = "course_detail")
    private String detail;
}