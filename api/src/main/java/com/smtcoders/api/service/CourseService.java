package com.smtcoders.api.service;

import com.smtcoders.api.entity.Course;
import com.smtcoders.api.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CourseService {

    Optional<Course> findByName(String email);


    String createNewCourse(Course newCourse);
}
