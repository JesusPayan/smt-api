package com.smtcoders.api.service;

import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
@Component
public class PaymentServiceImp implements PaymentService{
    @Autowired
    PaymentRepository repository;
    String returnMessage = "";

    public String addNewPayment(Payment newPayment) {
        log.info("Entrada al servicio de registros de pagos" + newPayment);

            try {
                repository.saveAndFlush(newPayment);
                returnMessage = "Pago Registrado con Exito";
            } catch (Exception e) {
                log.info(Arrays.toString(e.getStackTrace()));
                returnMessage = "No se pudo registrar el pago";
            }

        return returnMessage;
    }

    @Override
    public List<Payment> getAllPayments(Long id) {
       // return repository.findAllById(Collections.singleton(id));
        log.info("input que llega al controllador ");
        return repository.findAllPaymentsByUserId(id);
    }

}


