package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
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

//   @ManyToOne
//    @JoinColumn(name = "user_id") // Crea una FK en Payment hacia Customer
//    private Long user_id;

//    @ManyToOne
    @JoinColumn(name = "user_id") // FK hacia la tabla `user`
    private Long user_id;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "paymet_amount")
    private Double paymentAmount;
    @Column(name = "payment_dif")
    private Double paymentDif;
    @Lob
    @Column(name = "datos", columnDefinition = "LONGBLOB")
    private byte[] datos;


    public Payment() {
    }

    public Payment(Long id, String paymentStatus, Long user_id, String userName, LocalDate paymentDate, Double paymentAmount, Double paymentDif, byte[] datos) {
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.user_id = user_id;
        this.userName = userName;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentDif = paymentDif;
        this.datos = datos;
    }

    public Payment(Long id, String s, String paymentRecipetDate, byte[] fileBytes) {
    }


}