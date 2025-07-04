package com.smtcoders.api.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id_detail")
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "payment_id")
    //@PrimaryKeyJoinColumn
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentID;

    @OneToOne(mappedBy="paymentDetail")
    private PaymentReceipt  paymentReceipt;
    @Column(name = "payment_date")
    private Date paymentDate;

    /*
     @JoinColumn(name = "payment_recibe")
     @Column(name = "payment_student_receipt")
     private String paymentStudentReceipt;*/


}
