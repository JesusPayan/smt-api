package com.smtcoders.api.entity;

import jakarta.persistence.*;
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
    @Column(name = "teacher_id")
    private Long id;
    @Column(name = "teache_name")
    private String name;
    @Column(name = "teacher_email")
    private String email;
    @Column(name = "teacher_password")
    private boolean password;
}
