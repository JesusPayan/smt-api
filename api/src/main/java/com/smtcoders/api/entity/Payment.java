package com.smtcoders.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    //@JoinColumn(name = "user_id")
    //private Long userID;

    @Column(name = "payment_status")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id") // Crea una FK en Payment hacia Customer
    private User user;

    @Column(name = "payment_date")
    private String paymentDate;
    @Column(name = "paymet_amount")
    private Double paymentAmount;
    @Lob
    @Column(name = "datos", columnDefinition = "LONGBLOB")
    private byte[] datos;

    public Payment() {
    }

    public Payment(User user, String paymentStatus, String paymentDate, byte[] photo ) {
        this.user = user;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.datos= photo;
    }

    public Payment(User user, String pendiente, Date todayDate) {
    }
}
