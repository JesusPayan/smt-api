package com.smtcoders.api.controller;


import com.smtcoders.api.ApiResponse;
import com.smtcoders.api.entity.Student;
import com.smtcoders.api.repository.StudentRepository;
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

    @PostMapping("/register")
    public ApiResponse<Student> createStudent (@RequestBody Student student) {

        log.info("[Controlador:Student -> input:]" + student);
        Optional currentStudent = studentService.findByEmail(student.getEmail());
        ApiResponse<Student> response = new ApiResponse<>(
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
            if(studentService.createNewStudent(student).equals("Estudiante Guardado con Exito")){
                 response = new ApiResponse<>(
                        true,
                        "Estudiante Guardado con Exito",
                        student,
                        HttpStatus.OK.value()
                );
                return ResponseEntity.ok(response).getBody();
            }

        }
        return response;
    }
    private final String SECRET_KEY = "clave-secreta";
}
