package com.smtcoders.api.controller;

import com.smtcoders.api.ApiResponse;
import com.smtcoders.api.entity.Course;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.service.CourseService;
import com.smtcoders.api.service.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/courses")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService service;
    @Autowired
    private JwtUtil jwtUtil;
    ApiResponse<Course> response;
    @PostMapping("/register")
    public ApiResponse<Course> createCourse (@RequestBody Course course) {
        log.info("informacion que llega al controlador" + course.getCourseName() + course.getCourseLevel());
        Optional<Course> currentCourse = service.findByName(course.getCourseName());
        if (currentCourse.isPresent()) {
            response = new ApiResponse<>("Estudiante ya registrado", currentCourse, HttpStatus.BAD_REQUEST.value());
            response.setStatusCode(403);
            response.setData(course);
            response.setMessage("Curso ya registrado");
        } else {
            if (service.createNewCourse(course).equals("!Curso Guardado con exito¡ ")) {
                response.setSuccess(true);
                response.setMessage("Curso Guardado con exito");
                response.setData(course);
                response.setStatusCode(201);
                return ResponseEntity.ok(response).getBody();
            } else {
                response.setSuccess(true);
                response.setMessage("Error al guardar el curso");
                response.setData(course);
                response.setStatusCode(404);
                return ResponseEntity.ok(response).getBody();
            }
        }
        log.info(response.getMessage());
        return ResponseEntity.ok(response).getBody();
    }

}
