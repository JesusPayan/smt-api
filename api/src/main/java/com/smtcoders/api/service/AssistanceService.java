package com.smtcoders.api.service;

import com.smtcoders.api.dto.AssistanceDTO;
import com.smtcoders.api.entity.Assistance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssistanceService {


    String createNewAssistance(Long studentID);
    List<Assistance> getCurrentStudent(Long studentID);
}
