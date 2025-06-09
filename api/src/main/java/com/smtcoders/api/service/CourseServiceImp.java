package com.smtcoders.api.service;

import com.smtcoders.api.entity.Course;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class CourseServiceImp implements CourseService{
    @Autowired
    CourseRepository repository;

    @Override
    public Optional<Course> findByName(String name) {
        return repository.findByName(name);
    }



    @Override
    public String createNewCourse(Course newCourse) {
        String message;
        Optional<Course> currentCourse;
        log.info("Informacion que llega a la implementacion del servicio"+ newCourse.getCourseName() + newCourse.getCourseLevel() );
        try {
            repository.saveAndFlush(newCourse);
            message = "!Curso Guardado con exito¡";
        }catch (Exception e){
            log.info(Arrays.toString(e.getStackTrace()));
            message = "Error al guardar el curso";
        }
        log.info("Objeto User Actualizado" + newCourse.getCourseName(),newCourse.getCourseLevel());
        return message;
    }
}
