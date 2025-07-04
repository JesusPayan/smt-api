package com.smtcoders.api.controller;


import com.smtcoders.api.ApiResponse;
import com.smtcoders.api.dto.AuthResponse;
import com.smtcoders.api.dto.LoginRequest;
import com.smtcoders.api.entity.Resource;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.UserRepository;
import com.smtcoders.api.service.AuthService;
import com.smtcoders.api.service.EmailService;
import com.smtcoders.api.service.JwtUtil;
import com.smtcoders.api.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/students")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;
    ApiResponse<User> response;

    ApiResponse<List<User>> responseList;

    Optional <User> currentStudent;
    String message;
    boolean sucess,sucessSave;
    private  String returnMessage,token;
    @PostMapping("/register")
    public ApiResponse<User> createStudent (@RequestBody User user) {
        String saveMessage;
        log.info("[Controlador:User -> input:]" + user);

        currentStudent = userService.findByEmail(user.getEmail());
        if (currentStudent.isPresent()){
            response = new ApiResponse<>("Estudiante ya registrado",currentStudent,HttpStatus.BAD_REQUEST.value());
            response.setMessage("Estudiante ya registrado");
            response.setData(user);
            response.setStatusCode(403);
            log.info(response.getMessage());
            return ResponseEntity.ok(response).getBody();
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND).getBody();
        }else {
            saveMessage = userService.createNewStudent(user);
            if(saveMessage.equals("Estudiante Guardado con Exito")){
                emailService.sendSimpleMessage(user.getEmail(),"Registro SMART CODERS ACADEMY","Bienvenido " + user.getName() + " a SMART CODERS ACADEMY.\n" +
                        "Te compartimos tu contraseña de acceso:" + user.getPassword() + " con ella podras ingresar a tu portal." + "\n en estos dias te estaremos contactando, pero si te surge alguna duda nos puedes encontrar en el 6691429322 o escribiendonos un correo a mztcoders@gmail.com");
                        response = new ApiResponse<>(saveMessage, currentStudent, HttpStatus.CREATED.value());
                        log.info(response.getMessage());
//                return ResponseEntity.ok(response).getBody();
                response.setData(user);
                response.setSuccess(true);
                response.setMessage(saveMessage);
                log.info(response.getMessage());
                return ResponseEntity.ok(response).getBody();
            }else {
                response = new ApiResponse<User>(saveMessage, currentStudent, HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.ok(response).getBody();
            }
//            response = (userService.createNewStudent(user).equals("Estudiante Guardado con Exito")) ? new ApiResponse<User>("Estudiante Guardado con Exito", user, HttpStatus.OK.value()) :
//                    new ApiResponse<User>("Error al guardar al Usuario", currentStudent, HttpStatus.BAD_REQUEST.value());
//            log.info("Guardado del usuario"+ response.toString());
//            sucessSave = (response.isSuccess()) ? emailService.sendSimpleMessage(user.getEmail(), "Registro Smart Coders Academy", "Bienvenido " + user.getName() + " a SMART CODERS ACADEMY.\n" +
//                    "Te compartimos tu contraseña de acceso:" + user.getPassword() + " con ella podras ingresar a tu portal." + "\n en estos dias te estaremos contactando, pero si te surge alguna duda nos puedes encontrar en el 6691429322 o escribiendonos un correo a mztcoders@gmail.com") :
//                    false;
//            if (sucessSave) {
//                log.info("Usuario" + user.getName() + "Guardado Exitosamente");
//                return ResponseEntity.ok(response).getBody();
//            }else{
//
//            }
//            if(userService.createNewStudent(user).equals("Estudiante Guardado con Exito")){
//                boolean successSend;
//                response = new ApiResponse<>("Estudiante Guardado con Exito",
//                        user,
//                        HttpStatus.OK.value()
//                );
//                successSend = emailService.sendSimpleMessage(user.getEmail(),"Bienvienido "+ user.getName()+" a SmartCoders","Te compartimos tu contraseña de acceso:"+ user.getPassword());
//                if(successSend){
//                    log.info("Corre enviado exitosamente");
//                }else {
//                    log.info("Error al mandar el correo.");
//                }
//                return ResponseEntity.ok(response).getBody();
//            }
//
//        }
        }
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody LoginRequest loginRequest) throws Throwable {
        log.info("Login Request" + loginRequest.toString());
        currentStudent = userService.findByEmail(loginRequest.getEmail());
        User student;
        if(currentStudent.isPresent()){
            // convertimos el Optional de User en una entidad de estudiante
            student = currentStudent.orElseThrow(()->new NoSuchElementException("No se encontró la entidad"));
            log.info("El estudiante "+ student.getName() +" existe en la base de datos y se generara un token de acceso");
            // validamos que la contrasela ingresada sea la que el usuario tiene asociada
            returnMessage = (student.getPassword().equals(loginRequest.getPassword()))?"Credenciales correctas": "Credenciales Invalidas";
            token = jwtUtil.generateToken(student.getEmail());
            sucess = token != null && returnMessage.equals("Credenciales correctas");
            // Regresamos al front lo que el mensaje: Credenciales correctas, el usuario encontrado en la BD
            response = (!sucess) ? new ApiResponse<>(returnMessage, null, HttpStatus.UNAUTHORIZED.value()) : new ApiResponse<>(true, returnMessage, student, HttpStatus.OK.value(), token,student.getRole().toString());
            log.info(response.toString());

        }else {
            response = new ApiResponse<>(
                    returnMessage,
                    null,
                    HttpStatus.UNAUTHORIZED.value()

            );
        }

        log.info("Informacion que respondera el Api" + returnMessage +" "+token);

//        return ResponseEntity.ok(new AuthResponse(token));
            return  response;
    }
    @GetMapping("/{role}")
    public ResponseEntity<ApiResponse<List<User>>> showUsersByRole(@PathVariable String role){
        log.info("Informacion que llega al controlador para mostrar los usuarios por role" + role);
        List<User> userList;
        ApiResponse<List<User>> response = new ApiResponse<>();
        log.info("Controlador ShowUser by Role parametro de entrada" + role);
        if(!role.isEmpty()){
            userList = userService.findByRole(role);
            if (!userList.isEmpty()){
                response.setData(userList);
                response.setStatusCode(200);
                response.setMessage("Estudiantes Encontrados");
                response.setSuccess(true);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }else {

                response.setStatusCode(404);
                response.setMessage("Estudiantes No Encontrados");
                response.setSuccess(true);
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

            }


        }else {
            response.setStatusCode(404);
            response.setMessage("Estudiantes No Encontrados");
            response.setSuccess(true);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

        }
    }


    }
