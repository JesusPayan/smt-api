package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
@Table(name = "teacher")
public class Teacher  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_email")
    private String email;
    @Column(name = "teacher_password")
    private boolean password;

}
