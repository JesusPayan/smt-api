package com.smtcoders.api.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.security.Timestamp;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;



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
        @JoinColumn(name = "user_id")
        private User user;

}
/*
@Entity
@Table(name = "courses")
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;
        private String category;

        @Enumerated(EnumType.STRING)
        private Level level = Level.BASIC;

        @ManyToOne
        private User instructor;

        private Boolean isPublished = false;
        private Timestamp createdAt;

        public enum Level {
                BASIC, INTERMEDIATE, ADVANCED
        }
}
*/