package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_amount")
    private Double paymentAmount;


    @ManyToOne
    @JoinColumn(name = "student", referencedColumnName = "student_id")
    private Student student;

}
