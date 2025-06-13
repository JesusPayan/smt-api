package com.smtcoders.api.controller;

import com.smtcoders.api.ApiResponse;


import com.smtcoders.api.entity.Resource;
import com.smtcoders.api.dto.ResourceDTO;
import com.smtcoders.api.service.JwtUtil;
import com.smtcoders.api.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/resources")
@Slf4j
public class ResourseController {
    @Autowired
    private ResourceService service;
    @Autowired
    private JwtUtil jwtUtil;
    ApiResponse response = new ApiResponse();
//    @PostMapping("/register")
//    public ApiResponse<Resource> createCourse (@RequestBody List<Resource> resources) {
//
//
//        try {
//            for(Resource resource:resources){
//                log.info(resource.toString());
//            }
//        }catch (Exception e){
//            log.info(e.toString());
//            e.printStackTrace();
//        }
//
//
//
//        ApiResponse response = new ApiResponse(); // <-- esto es clave

//        response.setMessage("Curso creado correctamente");
//        response.setStatus(HttpStatus.CREATED.value());
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
        //Optional<Resource> currentCourse = service.findByName(course.getCourseName());
//        if (currentCourse.isPresent()) {
//            response = new ApiResponse<>("Estudiante ya registrado", currentCourse, HttpStatus.BAD_REQUEST.value());
//            response.setStatusCode(403);
//            response.setData(course);
//            response.setMessage("Curso ya registrado");
//        } else {
//            if (service(course).equals("!Curso Guardado con exito¡ ")) {
//                response.setSuccess(true);
//                response.setMessage("Curso Guardado con exito");
//                response.setData(course);
//                response.setStatusCode(201);
//                return ResponseEntity.ok(response).getBody();
//            } else {
//                response.setSuccess(true);
//                response.setMessage("Error al guardar el curso");
//                response.setData(course);
//                response.setStatusCode(404);
//                return ResponseEntity.ok(response).getBody();
//            }
//        }
//        log.info(response.getMessage());
//        return ResponseEntity.ok(response).getBody();
   //}
    String message;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Resource>>> showAllResourses(){
        log.info("Entro al controlador de los recursos");
        ApiResponse<List<Resource>> response = new ApiResponse<>();
        List<Resource> resourceList;
        resourceList = service.findAll();
        if (resourceList.isEmpty()){
            response.setStatusCode(200);
            response.setMessage("Recursos No Encontrados");
            response.setSuccess(false);
            response.setData(resourceList);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else {
            response.setStatusCode(200);
            response.setMessage("Recursos Encontrados");
            response.setSuccess(true);
            response.setData(resourceList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<List<ResourceDTO>>> createCourse(@RequestBody List<ResourceDTO> resources) {
        ApiResponse<List<ResourceDTO>> response = new ApiResponse<>();
        Resource resource;
        try {
            List<ResourceDTO> savedResources = new ArrayList<>();

            for (ResourceDTO resourceDTO : resources) {

                log.info("Recibido: {}",resourceDTO.toString());
                resource = Converter.converToEntity(resourceDTO);
                log.info(service.save(resource));

//                Resource saved =
//                log.info("Guardado: {}", service.save(resource)); // <-- aquí debes guardar el recurso
//                savedResources.add(saved);
            }

            response.setMessage("Curso(s) creado(s) correctamente");
            response.setStatusCode(201);
            response.setData(savedResources);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Error al registrar curso(s): {}", e.getMessage(), e);

            response.setMessage("Error al registrar curso(s)");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ResourceDTO>> updateResource(@RequestBody ResourceDTO currResource) {
        Resource currentResource;
        log.info("Informacion que llega al controlador" + currResource.getId());
        // Casteamos el objeto DTO para convertirlo a un entity de Resource
        currentResource = Converter.converToEntity(currResource);

        // Enviamos al servicio para implementar la logica de negocio
        service.updateCurrentResource(currentResource);
        response.setMessage("Recurso Actualizado con Exito");
        response.setStatusCode(200);
        response.setData(currResource);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<ResourceDTO>> deleteResource(@RequestBody ResourceDTO currResource){
        Resource currentResource;
        log.info("Informacion que llega al controlador"+ currResource.getId());
        // Casteamos el objeto DTO para convertirlo a un entity de Resource
        currentResource = Converter.converToEntity(currResource);

        // Enviamos al servicio para implementar la logica de negocio
        service.deleteCurrentResource(currentResource);
        response.setMessage("Recurso Eliminado con Exito");
        response.setStatusCode(200);
        response.setData(currResource);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
