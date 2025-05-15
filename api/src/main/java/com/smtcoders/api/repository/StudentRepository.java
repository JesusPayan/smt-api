package com.smtcoders.api.repository;

import com.smtcoders.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "Select * from Student where student_email=?",nativeQuery = true)
    Optional findByEmail(String email);
}
