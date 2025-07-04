package com.smtcoders.api.repository;

import com.smtcoders.api.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    //Registrar el primer pago pendiente del estudiante
    //@Query(value = "Select * from User where user_email=?",nativeQuery = true)
    //@Modifying
    //@Query(value = "insert into Payment(payment_status,userid) values (=?,=?) commit;",nativeQuery = true)
    //void InsertFirstRegisterPayment(String paymentStatus,Long StudentID);
    @Query(value = "select * from payment where user=?",nativeQuery = true)
    List<Payment> findAllPaymentsByUserId(Long id);
    @Query(value = """
    SELECT 
      p.payment_id AS paymentId,
      u.user_name AS userName,
      p.datos,
      p.paymet_amount AS paymentAmount,
      p.payment_date AS paymentDate,
      p.payment_dif AS paymentDif,
      p.payment_status AS paymentStatus,
      u.next_payment AS nextPayment
    FROM payment p
    LEFT JOIN user u ON u.user_id = p.user_id
    """, nativeQuery = true)
    List<Object[]> findAllRaw();
    List<Payment> findAll();


}
