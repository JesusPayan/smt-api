package com.smtcoders.api.service;

import com.smtcoders.api.dto.LoginRequest;
import com.smtcoders.api.dto.LoginResponse;
import com.smtcoders.api.entity.User;
import com.smtcoders.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) throws Throwable {
        // Buscar al usuario por su email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Validar contraseña (esto asume que las contraseñas están en texto plano, lo cual NO es recomendable)
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // Generar token JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token, user.getRole().name());
    }
}
