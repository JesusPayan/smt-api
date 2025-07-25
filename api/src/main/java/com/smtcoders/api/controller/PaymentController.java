package com.smtcoders.api.controller;

import com.smtcoders.api.ApiResponse;
//import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.dto.PaymentDTO;
import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.entity.PaymentStatus;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.UserRepository;
//import com.smtcoders.api.service.PaymentService;
import com.smtcoders.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.Converter;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/payments")
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
//    public ApiResponse<PaymentDTO> registerPayment (@RequestBody PaymentDTO newPayment, @RequestParam MultipartFile recibo){
    public ApiResponse<Payment> registerPayment (
            @RequestParam("amount") Double amount,
            @RequestParam("studentId")Long studentID,
            @RequestParam("file") MultipartFile receipt) throws IOException {
        log.info("Informacion que llega al controlador",amount,studentID,receipt);
        Payment savePayment = new Payment();
        savePayment.setPaymentAmount(amount);
        LocalDate today = LocalDate.now();

        savePayment.setPaymentDate(today);
        String paymentStatus;
        if (amount == 500){
            paymentStatus = PaymentStatus.COMPLETO.toString();
        }else{
            paymentStatus = PaymentStatus.INCOMPLETO.toString();
        }
        savePayment.setPaymentStatus(paymentStatus);
        savePayment.setPaymentDif(amount-500);
//        savePayment.setUser(studentID);
//        savePayment.setUser(studentID);
        savePayment.setDatos(receipt.getInputStream().readAllBytes());
        paymentService.addNewPayment(savePayment);
        ApiResponse<Payment> response = new ApiResponse<>();

        response.setMessage("Pago Registrado con Exito");
        response.setStatusCode(HttpStatus.OK.value());

        return response;


    }
//    ApiResponse<Payment> registrePayment(
//            @RequestParam("amount")Double amount,
//            @RequestParam("date")String paymentRecipetDate,
//            @RequestParam("studentId")Long studentID,
//            @RequestParam("file") MultipartFile receipt) throws ParseException, IOException {
//        ApiResponse<Payment> response;
//        System.out.println("Entro al servicio para registro de Pago");
//        log.info("amount "+amount +" Date "+ paymentRecipetDate +" StudentID "+studentID );
////        Optional<User> currentStudent = userRepository.findById(studentID);
////        User user = currentStudent.get();
////       /* SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
////        Date currentDate = formato.parse(paymentRecipetDate);*/
////        byte[] fileBytes = receipt.getInputStream().readAllBytes();
////        Payment newPayment = new Payment(user.getId(),"-",paymentRecipetDate,fileBytes);
////        if(paymentService.addNewPayment(newPayment).equals("Pago registrado con exito")){
////            response = new ApiResponse<>(
////                    true,
////                    "Pago Registrado con Exito",
////                    newPayment,
////                    HttpStatus.OK.value()
////            );
////        }else{
////            response = new ApiResponse<>(
////                    false,
////                    "Pago Guardado con Exito",
////                    newPayment,
////                    HttpStatus.BAD_REQUEST.value()
////            );
////        }
//
//        return response;
//    }
    //http://localhost:8080/api/auth/payments/student/6

    @GetMapping("/student/{studentId}")
    List<Payment> getAllPaymentbyid(@PathVariable Long studentId){
      log.info("Input que llega a controlador Payment "+ studentId);
      return   paymentService.getAllPayments(studentId);
    }
    //controlador para visualizar todos los pagos de los estudiantes
    @GetMapping("/all")
    List<Payment> getAllPayment(){
        log.info("Entra al controlador de pagos para visualizar todos los pagos");
        return   paymentService.getAllPayments();
    }
}
