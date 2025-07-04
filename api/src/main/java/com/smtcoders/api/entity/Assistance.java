package com.smtcoders.api.entity;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "assistance")
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long id;

    @Column(name = "assistance_date")
    private LocalDate currentDate;
//    @Column(name = "student_present")
//    private Boolean isPresent;


    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private Long userID;

    public Assistance() {
    }

    public Assistance(LocalDate currentDate, Long userID) {
        this.currentDate = currentDate;
        this.userID = userID;
    }

}
