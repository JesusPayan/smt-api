package com.smtcoders.api.entity;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "assistance")
public class Assistance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long id;

    @Column(name = "assistance_date")
    private Date currentDate;
    @Column(name = "student_present")
    private Boolean isPresent;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private User user;
}
