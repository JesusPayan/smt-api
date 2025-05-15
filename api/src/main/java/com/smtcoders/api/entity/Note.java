package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
@Table(name = "note")
public class Note implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private Long id;
/*
    //@ManyToOne
    @JoinColumn
    @Column(name = "student_id")
    private Long studentID;

    //@ManyToOne
    @JoinColumn
    @Column(name = "course_id")
    private Long courseID;
*/
    @Column(name = "noteNumber")
    private Double note;

    @Column(name = "note_timestamp")
    private Date noteTimeStamp;


}
