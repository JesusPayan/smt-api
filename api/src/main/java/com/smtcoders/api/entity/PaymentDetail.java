package com.smtcoders.api.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "PaymentDetail")
public class PaymentDetail {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Payment payment;

    private Double amount;
    private LocalDate date;

}
