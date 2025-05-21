package com.smtcoders.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


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

    @Column(name = "payamente_student_receipt")
    private String paymenteStudentReceipt;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Long user;
    
}
