package com.smtcoders.api.service;

import com.smtcoders.api.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {


    String addNewPayment(Payment newPayment);

    List<Payment> getAllPayments(Long id);
    List<Payment> getAllPayments();
}
