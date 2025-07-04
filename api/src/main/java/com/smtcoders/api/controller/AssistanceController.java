package com.smtcoders.api.controller;

import com.smtcoders.api.ApiResponse;
import com.smtcoders.api.dto.AssistanceDTO;
import com.smtcoders.api.entity.Assistance;
import com.smtcoders.api.entity.Resource;
import com.smtcoders.api.service.AssistanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/assistance")
@Slf4j
public class AssistanceController {

    @Autowired
    AssistanceService service;
    ApiResponse<Assistance> response = new ApiResponse<>();
    String message;
    int statusCode;

    @PostMapping("/register/{studentId}")
    public ApiResponse<Assistance> registerAssistance(@PathVariable("studentId")  Long studentID){
        log.info("Informacion que entra al controlador Registro de asistencias :" + studentID);
        try{
            String result = service.createNewAssistance(studentID);

            if("Asistencia Registrada con exito".equals(result)){
//                log.error(result + "para el estudiante con el identificador" + studentID);
                response.setMessage(result);
                response.setSuccess(true);
                response.setStatusCode(200);
            }else {
//                log.error(result + "para el estudiante con el identificador" + studentID);
                response.setMessage(result);
                response.setSuccess(false);
                response.setStatusCode(400);
            }

            } catch (Exception e) {
            log.error("Error al registrar asistencia: ", e);
            response.setMessage("Error interno del servidor");
            response.setStatusCode(400);
        }
        log.info("Respuesta del controlador para registrar la asistencia del estudiante ["+studentID+"] "+ response.getMessage()+" "+ response.getStatusCode());
        return response;
    }

    @GetMapping("/{studentId}")
//    /public List<AssistanceDTO> getStudentAssistance(@PathVariable("studentId")  Long studentID)
    public ResponseEntity<ApiResponse<List<Assistance>>> getStudentAssistance(@PathVariable("studentId")  Long studentID) {
        ApiResponse<List<Assistance>> response = new ApiResponse<>();
        List<Assistance> resourceList;
        log.info("Informacion que entra al controlador Obtener asistencias del estudiante :" + studentID);

        resourceList = service.getCurrentStudent(studentID);
        if (resourceList.isEmpty()) {
            response.setStatusCode(200);
            response.setMessage("Recursos No Encontrados");
            response.setSuccess(false);
            response.setData(resourceList);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setStatusCode(200);
            response.setMessage("Recursos Encontrados");
            response.setSuccess(true);
            response.setData(resourceList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
