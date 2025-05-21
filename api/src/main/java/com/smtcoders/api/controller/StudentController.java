package com.smtcoders.api.controller;


import com.smtcoders.api.ApiResponse;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.StudentRepository;
import com.smtcoders.api.service.EmailService;
import com.smtcoders.api.service.StudentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public ApiResponse<User> createStudent (@RequestBody User user) {

        log.info("[Controlador:User -> input:]" + user);
        Optional currentStudent = studentService.findByEmail(user.getEmail());
        ApiResponse<User> response = new ApiResponse<>(
                false,
                "Estudiante ya registrado",
                null,
                HttpStatus.BAD_REQUEST.value()
        );
        if (currentStudent.isPresent()){
            response = new ApiResponse<>(
                    false,
                    "Estudiante ya registrado",
                    null,
                    HttpStatus.BAD_REQUEST.value()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND).getBody();
        }else{
            if(studentService.createNewStudent(user).equals("Estudiante Guardado con Exito")){
                boolean successSend;
                response = new ApiResponse<>(
                        true,
                        "Estudiante Guardado con Exito",
                        user,
                        HttpStatus.OK.value()
                );
                successSend = emailService.sendSimpleMessage(user.getEmail(),"Bienvienido "+ user.getName()+" a SmartCoders","Te compartimos tu contraseña de acceso:"+ user.getPassword());
                if(successSend){
                    log.info("Corre enviado exitosamente");
                }else {
                    log.info("Error al mandar el correo.");
                }
                return ResponseEntity.ok(response).getBody();
            }

        }
        return response;
    }
    private final String SECRET_KEY = "clave-secreta";
}
