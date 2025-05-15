package com.smtcoders.api.entity;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "assistance")
public class Assistance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long id;

    @Column(name = "assistance    _date")
    private Date currentDate;
    @Column(name = "student_present")
    private Boolean isPresent;

    @ManyToOne
    @JoinColumn(name = "student", referencedColumnName = "student_id")
    private Student student;
}
