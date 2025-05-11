package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Long student;


    @Column(name = "payment_date")
    private Date paymentDate;
}
