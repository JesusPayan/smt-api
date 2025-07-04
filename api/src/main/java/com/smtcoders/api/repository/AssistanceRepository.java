package com.smtcoders.api.repository;

import com.smtcoders.api.entity.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssistanceRepository extends JpaRepository<Assistance,Long> {

    @Query(value = "select * from Assistance where userid = ?",nativeQuery = true)
    public List<Assistance>getCurrentStudentAssistance(Long studentID);



}
