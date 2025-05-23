package com.smtcoders.api.repository;

import com.smtcoders.api.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    //Registrar el primer pago pendiente del estudiante
    //@Query(value = "Select * from User where user_email=?",nativeQuery = true)
    //@Modifying
    //@Query(value = "insert into Payment(payment_status,userid) values (=?,=?) commit;",nativeQuery = true)
    //void InsertFirstRegisterPayment(String paymentStatus,Long StudentID);


}
