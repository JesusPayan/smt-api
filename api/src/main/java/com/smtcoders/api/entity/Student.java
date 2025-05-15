package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import utils.PasswordGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;



@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name",nullable = false)
    private String name;
    @Column(name = "student_email",unique = true,nullable = false)
    private String email;
    @Column(name = "student_phone",nullable = false)
    private String phone;
    @Column(name = "student_create_timestamp",nullable = false)
    private LocalDate createTimeStamp;
    @Column(name = "student_status", nullable = false)
    private boolean status;
    @Column(name = "student_password")
    private String password;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Course> studentCourseList;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Assistance> studentAssistanceList;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Payment> studenPaymentList;


}
