package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "resource")
public class Resource  {

        @Id

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "resourse_name")
        private String name;

        @Column(name = "carrier_path")
        private String carrierPath;
        @Column(name = "type")
        private String type;
        @Column(name = "technology_stack")
        private String tecnologyStack;
        @Column(name = "point")
        private Integer score;
        @Column(name = "description")
        private String description;
        @Column(name = "available")
        private String available;

        public Resource(String name, String carrierPath, String type, String tecnologyStack, Integer score, String description, String available) {

                this.name = name;
                this.carrierPath = carrierPath;
                this.type = type;
                this.tecnologyStack = tecnologyStack;
                this.score = score;
                this.description = description;
                this.available = available;
        }


//        @Column(name ="course_description")
//        private String description;
//        @Column(name ="course_start_date")
//        private Date courseStart;
//        @Column(name ="course_end_date")
//        private Date courseEnd;

//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        private User user;

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