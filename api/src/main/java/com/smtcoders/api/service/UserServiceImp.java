package com.smtcoders.api.service;

//import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.entity.Role;
import com.smtcoders.api.entity.User;
//import com.smtcoders.api.repository.PaymentRepository;
import com.smtcoders.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.PasswordGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static com.smtcoders.api.entity.Role.ADMIN;
import static com.smtcoders.api.entity.Role.ESTUDIANTE;


@Component
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    //@Autowired
//    PaymentRepository paymentRepository;
    User currentUser;
    String decodePassword,encodePassword;
    List<User> userList;

    @Override
    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);
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
            //newUser.setCreateTimeStamp(LocalDate.now());
            newUser.setUserStatus(true);
            newUser.setRole(ESTUDIANTE);
            try {
                userRepository.saveAndFlush(newUser);
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

    @Override
    public List<User> findByRole(String role) {
        log.info("Informacion que llega a la implementacion del servicio" + role);
        List<User> auxList = userRepository.findAll().stream().toList();

        switch (role){
            case "ESTUDIANTE":
                userList = auxList.stream().filter(user -> user.getRole().name().equals("ESTUDIANTE")).toList();
            break;
            case "ADMIN":
                userList = userRepository.findAll().stream().filter(elements-> elements.getRole().equals(ADMIN.name())).toList();
            break;
        }
        System.out.println(userList);

        return userList;
    }
}

