package com.smtcoders.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate createTimeStamp;
    private boolean status;

}
