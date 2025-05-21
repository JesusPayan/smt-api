package com.smtcoders.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_description",nullable = false)
    private String name;


    //@JoinColumn(name = "user_id")
    //private Long user;
    //private Long user;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User> userList;
}
