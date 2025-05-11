package com.smtcoders.api.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    private LocalDate fechaInscripcion;
}
