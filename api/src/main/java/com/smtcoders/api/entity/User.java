package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(name = "user_email",unique = true,nullable = false)
    private String email;
    @Column(name = "user_phone",nullable = false)
    private String phone;
    @Column(name = "user_create_timestamp",nullable = false)
    private LocalDate createTimeStamp;
    @Column(name = "user_status", nullable = false)
    private boolean status;
    @Column(name = "user_password")
    private String password;

    //@ManyToOne

    @JoinColumn(name = "role_id")
    @Column(name = "role_id")
    private Long role;


    //@OneToOne//(mappedBy = "role",cascade = CascadeType.ALL)
    //private List<Role> roleList;
    //private Role role;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Course> userCourseList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Assistance> userAssistanceList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment> studenPaymentList;


}

/*
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    private Timestamp createdAt;

    public enum Role {
        STUDENT, INSTRUCTOR, ADMIN
    }
}
*/