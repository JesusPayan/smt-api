package com.smtcoders.api.dto;

import com.smtcoders.api.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createTimeStamp;
    private boolean status;
    private String password;
    private String userPictureUrl;
    private String  score;
    private LocalDate nextPayment;
    private String paymentStatus;
    private LocalDate lastAssistance;
    private Long assistanceCounter;

    @Enumerated(EnumType.STRING)
    private Role role;
}
