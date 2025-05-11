package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;
    @Column(name = "student_email")
    private String email;
    @Column(name = "student_phone")
    private String phone;
    @Column(name = "student_create_timestamp")
    private LocalDate createTimeStamp;
    @Column(name = "student_status")
    private boolean status;
    @Column(name = "student_password")
    private boolean password;

}
