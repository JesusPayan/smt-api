package com.smtcoders.api.service;

import com.smtcoders.api.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional findByEmail(String email);
    String createNewStudent(User newUser);
    List<User> findByRole(String  role);
}
