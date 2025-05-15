package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "course")
public class Course implements Serializable {

        @Id
        @GeneratedValue
        @Column(name = "course_id")
        private Long id;
        @Column(name = "course_name")
        private String courseName;
        @Column(name ="course_description")
        private String description;
        @Column(name ="course_start_date")
        private Date courseStart;
        @Column(name ="course_end_date")
        private Date courseEnd;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;



}
