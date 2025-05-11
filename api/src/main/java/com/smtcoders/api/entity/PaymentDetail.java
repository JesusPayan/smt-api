package com.smtcoders.api.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "payment_detail")
public class PaymentDetail {
    @Id
    @GeneratedValue
    @Column(name = "payment_id_detail")
    private Long id;

    @ManyToOne
    @Column(name = "payment_id")
    private Long paymentID;

    @Column(name = "payment_amount")
    private Double amount;
    @Column(name = "payment_date")
    private LocalDate date;

}
