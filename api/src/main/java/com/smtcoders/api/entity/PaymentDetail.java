package com.smtcoders.api.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "payment_id_detail")
    private Long id;
/*
    @ManyToOne
    @JoinColumn
    private Payment paymentID;
*/

/*
    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;
*/
}
