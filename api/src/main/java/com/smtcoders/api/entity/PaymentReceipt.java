package com.smtcoders.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_recibe")
public class PaymentReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "payment_detail")
    private PaymentDetail paymentDetail;

    @Lob
    @Column(name = "datos", columnDefinition = "LONGBLOB")
    private byte[] datos;

}
