package com.smtcoders.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String correo;
    private String especialidad;
    private String telefono;

}
