package com.smtcoders.api.service;
import com.smtcoders.api.dto.ResourceDTO;
import com.smtcoders.api.entity.Resource;
import com.smtcoders.api.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ResourceServiceImp implements ResourceService{
    @Autowired
    ResourceRepository repository;

    @Override
    public Optional<Resource> findByName(String name) {
        return repository.findByName(name);
    }
    String message;



    @Override
    public String save(Resource newResource) {
        String message;
        Optional<Resource> currentCourse = null;
//        log.info("Informacion que llega a la implementacion del servicio"+ newResource.getCourseName() + newResource.getCourseLevel() );
        try {
            repository.saveAndFlush(newResource);
            message = "!Curso Guardado con exito¡";
        }catch (Exception e){
            log.info(Arrays.toString(e.getStackTrace()));
            message = "Error al guardar el curso";
        }

        log.info("Objeto User Actualizado" + newResource);
        return message;
    }

    @Override
    public List<Resource> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Resource> findByID(Long id) {
        return this.repository.findById(id);
    }




    @Override
    public String updateCurrentResource(Resource resource) {
        Optional<Resource> currentResource;
        log.info(String.valueOf(resource.getName()));
        currentResource = findByName(resource.getName());
        if(currentResource.isPresent()){

            repository.save(resource);
            message = "Recurso Actualizado con exito";
        }
        return message;
    }

    @Override
    public String deleteCurrentResource(Resource resource) {


        repository.delete(resource);

        return "Recurso Eliminado";
    }
}
