package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {

        @Id
        @GeneratedValue
        @Column(name = "id")
        private Long id;

        @Column(name = "course_name")
        private String courseName;
        @Column(name ="course_description")
        private String descripcion;


        @ManyToOne
        @Column(name ="teacher_id")
        private Long teacherID;
        @Column(name ="course_start")
        private Date courseStart;
        @Column(name ="course_end")
        private Date courseEnd;

}
