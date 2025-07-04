package com.smtcoders.api.service;


import com.smtcoders.api.entity.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ResourceService {

    Optional<Resource> findByName(String email);


    String save(Resource newResource);

    List<Resource> findAll();
    Optional<Resource> findByID(Long id);

//    String updateCurrentResourse(Resource currentResource);

    String updateCurrentResource(Resource resource);
    void deleteCurrentResource(Long ID);
}
