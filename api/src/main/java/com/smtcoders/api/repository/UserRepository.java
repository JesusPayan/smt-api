package com.smtcoders.api.repository;

import com.smtcoders.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select * from User where user_email=?",nativeQuery = true)
    Optional<User> findByEmail(String email);
}
