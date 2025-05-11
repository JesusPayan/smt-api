package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Course")
public class Course {

        @Id
        @GeneratedValue
        private Long id;

        private String nombre;
        private String descripcion;
        private String nivel; // Básico, Intermedio, Avanzado

        @ManyToOne
        private Teacher teacher;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;

}
