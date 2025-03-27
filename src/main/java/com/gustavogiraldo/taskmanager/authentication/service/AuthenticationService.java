package com.gustavogiraldo.taskmanager.authentication.service;

import com.gustavogiraldo.taskmanager.authentication.dto.AuthRequest;
import com.gustavogiraldo.taskmanager.authentication.dto.AuthResponse;
import com.gustavogiraldo.taskmanager.authentication.dto.RegisterRequest;
import com.gustavogiraldo.taskmanager.authentication.security.JwtUtil;
import com.gustavogiraldo.taskmanager.user.entity.Role;
import com.gustavogiraldo.taskmanager.user.entity.User;
import com.gustavogiraldo.taskmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está en uso");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptar password
        user.setRole(Role.USER); // Asignar rol por defecto
        userRepository.save(user);

        String token = jwtUtil.getToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        String exceptionMessage = "credenciales inválidas";

        User user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, exceptionMessage));

        // Verificar manualmente la contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exceptionMessage);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.getToken(user);
        return new AuthResponse(token);
    }
}