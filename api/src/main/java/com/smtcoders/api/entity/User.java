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

    /*
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Role> roleList;*/

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Course> userCourseList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Assistance> userAssistanceList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment> studenPaymentList;


}
