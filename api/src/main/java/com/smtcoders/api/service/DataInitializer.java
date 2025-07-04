package com.smtcoders.api.service;

import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.entity.Role;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.PaymentRepository;
import com.smtcoders.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public void run(String... args) throws Exception {



        if (userRepository.findByEmail("admin@academia.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@academia.com");
            admin.setPassword("admin123"); // Idealmente, cifrada con BCrypt
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("estudiante1@academia.com").isEmpty()) {
            User estudiante = new User();
            estudiante.setEmail("estudiante1@academia.com");
            estudiante.setPassword("estudiante123");
            estudiante.setRole(Role.ESTUDIANTE);
            userRepository.save(estudiante);
        }
//        if(paymentRepository.findAll().isEmpty()){
//            LocalDate newDate =LocalDate.now();
//
//            Payment payment = new Payment();
//            payment.setPaymentAmount(500.00);
//            payment.setPaymentDate(newDate);
//            payment.setUser(userRepository.findByEmail("estudiante1@academia.com").orElseThrow());
//
//        }


    }
}