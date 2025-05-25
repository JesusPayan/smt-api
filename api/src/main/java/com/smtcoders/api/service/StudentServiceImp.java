package com.smtcoders.api.service;

import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.entity.Role;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.PaymentRepository;
import com.smtcoders.api.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.PasswordGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;


@Component
@Slf4j
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PaymentRepository paymentRepository;
    User currentUser;
    String decodePassword,encodePassword;


    @Override
    public Optional findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public String createNewStudent(User newUser){
        String message ="";
        //User student = new User();
        log.info("[Service:User:Create New]" + newUser);
            decodePassword = PasswordGenerator.generatePassword(8);
            log.info("Password decodificada -> " + decodePassword);
            encodePassword = PasswordGenerator.encodePassword(decodePassword);
            log.info("Password decodificada -> " + encodePassword);
            newUser.setPassword(encodePassword);
            newUser.setCreateTimeStamp(LocalDate.now());
            newUser.setStatus(true);
            newUser.setRole(1L);
            try {
                studentRepository.saveAndFlush(newUser);
                //paymentRepository.InsertFirstRegisterPayment("Pendiente",newUser.getId());
               // paymentRepository.saveAndFlush(createNewFirstPayment(newUser  ));
                message = "Estudiante Guardado con Exito";
            }catch (Exception e) {
                log.info(Arrays.toString(e.getStackTrace()));
                message = "Estudiante no se pudo Registrar";
            }
        log.info("Objeto User Actualizado" + newUser.getName()+ newUser.getEmail());
        return message;
    }

    private Payment createNewFirstPayment(User user){
        Date today = new Date();
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = sdf.format(todayDate);
        Payment newFirstPayment = new Payment(user,"Pendiente", todayDate);

        return newFirstPayment;
    }

}

