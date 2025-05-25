package com.smtcoders.api.service;

import com.smtcoders.api.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {


    String addNewPayment(Payment newPayment);
}
