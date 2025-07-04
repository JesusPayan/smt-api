package com.smtcoders.api.service;

import com.smtcoders.api.dto.AssistanceDTO;
import com.smtcoders.api.dto.UserDTO;
import com.smtcoders.api.entity.Assistance;
import com.smtcoders.api.entity.PaymentStatus;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.AssistanceRepository;
import com.smtcoders.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Converter;
import utils.PaymentUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AssistanceServiceImp implements AssistanceService {
    String message;

    @Autowired
    AssistanceRepository repository;
    @Autowired
    UserRepository userRepository;

    PaymentUtils paymentUtil = new PaymentUtils();


    @Override
    public String createNewAssistance(Long studentID){

        LocalDate currentDate = LocalDate.now();
        User currentStudent = userRepository.findById(studentID).orElseThrow();
        log.info("Informacion del usuario actual:"+currentStudent.getLastAssistance()+" "+ currentStudent.isUserStatus());
        log.info("Estatus del usuario: "+ currentStudent.isUserStatus());
        log.info("Estatus del pago del usuario "+ currentStudent.getPaymentStatus());
        if(currentStudent.getLastAssistance()==null || !currentStudent.getLastAssistance().equals(currentDate)){
            repository.save(new Assistance(currentDate,studentID));
            currentStudent.setLastAssistance(currentDate);
            userRepository.save(currentStudent);
            message = "Asistencia Registrada con exito";
        } else if (!currentStudent.isUserStatus() || !paymentUtil.getValidateStatus(currentStudent.getPaymentStatus()) ) {
            message = "Usuario desactivado";
            log.info(message);
        }else if(currentStudent.getLastAssistance().equals(currentDate)){
            message = "Solo se puede registrar una asistencia por dia";
        }
        return message;
    }

    @Override
    public List<Assistance> getCurrentStudent(Long studentID) {
        //        List<AssistanceDTO> currentEstudentAsistanceDTO = new ArrayList<>();

//        for (Assistance element:currentEstudentAsistance){
//            AssistanceDTO assistance = new AssistanceDTO(element.getId(),element.getCurrentDate());
//            currentEstudentAsistanceDTO.add(assistance);
//        }
//

        return repository.getCurrentStudentAssistance(studentID);
    }
}
