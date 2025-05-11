package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Notes")
public class Notes {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student estudiante;

    @ManyToOne
    private Course course;

    private String modulo; // nombre del tema o clase
    private LocalDate fecha;
    private String estado; // Completado, En progreso, Pendiente
}
