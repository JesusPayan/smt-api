package com.smtcoders.api.repository;

import com.smtcoders.api.entity.Course;
import com.smtcoders.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(value = "Select * from Course where course_name=?",nativeQuery = true)
    Optional<Course> findByName(String name);

}
