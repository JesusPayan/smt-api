package com.smtcoders.api.service;

import com.smtcoders.api.entity.Student;
import com.smtcoders.api.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.PasswordGenerator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;


@Component
@Slf4j
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    Student currentStudent;
    String decodePassword,encodePassword;


    @Override
    public Optional findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public String createNewStudent(Student newStudent){
        String message ="";
        //Student student = new Student();
        log.info("[Service:Student:Create New]" + newStudent);
            decodePassword = PasswordGenerator.generatePassword(8);
            log.info("Password decodificada -> " + decodePassword);
            encodePassword = PasswordGenerator.encodePassword(decodePassword);
            log.info("Password decodificada -> " + encodePassword);
            newStudent.setPassword(encodePassword);
            newStudent.setCreateTimeStamp(LocalDate.now());
            newStudent.setStatus(true);
            try {
                studentRepository.saveAndFlush(newStudent);
                message = "Estudiante Guardado con Exito";
            }catch (Exception e){
                log.info(Arrays.toString(e.getStackTrace()));
                message = "Estudiante no se pudo Registrar";
            }


        log.info("Objeto Student Actualizado" + newStudent.getName()+newStudent.getEmail());
        return message;
    }
}
