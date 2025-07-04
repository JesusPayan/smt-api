package com.smtcoders.api.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import utils.Mail;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendSimpleMessage(String to, String subject, String text) {
        log.info("Entra al servicio de envio de correo: receptor"+to +"Subject:"+subject +"Cuerpo de correo:"+text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mztcoders@gmail.com"); // Opcional, pero recomendable
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        return true;
    }

}