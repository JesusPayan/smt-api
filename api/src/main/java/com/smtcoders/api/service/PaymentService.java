package com.smtcoders.api.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    Boolean registreFirstTimeStudent(String paymentStatus,Long StudentID);
}
