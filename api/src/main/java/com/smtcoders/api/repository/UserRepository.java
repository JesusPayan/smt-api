package com.smtcoders.api.repository;

import com.smtcoders.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select * from User where user_email=?",nativeQuery = true)
    Optional<User> findByEmail(String email);
//    @Query(value = "Update last_assistance=? whe")
//@Query("SELECT new com.smtcoders.api.dto.PaymentDTO(p.id, u.userName, p.paymentAmount, p.paymentDate, p.paymentStatus) " +
//        "FROM Payment p LEFT JOIN p.user u")
//List<PaymentDTO> findCustomPayments();

//    @Query("SELECT new com.smtcoders.api.dto.UserDTO(u.id,u.name FROM User where u.role=?")
//    List<User> findByRole(String role);
}
