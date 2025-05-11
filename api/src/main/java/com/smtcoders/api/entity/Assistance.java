package com.smtcoders.api.entity;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Assistance")
public class Assistance {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
    @Column(name = "current_date")
    private Data currentDate;
    @Column(name = "Assitance")
    private Boolean assistance;
}
