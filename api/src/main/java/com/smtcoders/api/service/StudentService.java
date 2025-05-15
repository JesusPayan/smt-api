package com.smtcoders.api.service;

import com.smtcoders.api.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StudentService {

    Optional findByEmail(String email);
    String createNewStudent(Student newStudent);
}
