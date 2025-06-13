package com.smtcoders.api.repository;


import com.smtcoders.api.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {
//    @Query(value = "Select * from Course where resourse_name=?",nativeQuery = true)
    Optional<Resource> findByName(String name);

}
