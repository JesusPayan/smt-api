package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email",unique = true)
    private String email;
    @Column(name = "user_phone")
    private String phone;
    @Column(name = "create_time_stamp", nullable = false, updatable = false)
    private LocalDateTime createTimeStamp;
    @Column(name = "user_status")
    private boolean status;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_picture")
    private String userPictureUrl;


    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Course> userCourseList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Assistance> userAssistanceList;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Payment> studenPaymentList;
    @PrePersist
    protected void onCreate() {
        this.createTimeStamp = LocalDateTime.now();
    }

}

