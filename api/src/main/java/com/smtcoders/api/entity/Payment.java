package com.smtcoders.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userID;

    @Column(name = "payment_status")
    private String paymentStatus;

    public Payment() {
    }
    @ManyToOne
    @JoinColumn(name = "user_id") // Crea una FK en Payment hacia Customer
    private User user;

    public Payment(Long userID, String paymentStatus) {
        this.userID = userID;
        this.paymentStatus = paymentStatus;
    }

}
