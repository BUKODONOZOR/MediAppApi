package com.empleabilidad.empleabilidad.Services;

import com.empleabilidad.empleabilidad.Dtos.LoginDTO;
import com.empleabilidad.empleabilidad.Dtos.LoginResponse;
import com.empleabilidad.empleabilidad.Dtos.RegisterDTO;
import com.empleabilidad.empleabilidad.Exceptions.BadRequestException;
import com.empleabilidad.empleabilidad.Exceptions.ResourceNotFoundException;
import com.empleabilidad.empleabilidad.Exceptions.UnauthorizedException;
import com.empleabilidad.empleabilidad.Models.User;
import com.empleabilidad.empleabilidad.Repositories.UserRepository;
import com.empleabilidad.empleabilidad.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<LoginResponse> login(LoginDTO loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (!userOptional.isPresent()) {
            throw new UnauthorizedException("Invalid email or password.");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password.");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(new LoginResponse("Login successful for " + user.getRole().name().toLowerCase() + "!", token));
    }

    public ResponseEntity<String> register(RegisterDTO registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already in use.");
        }

        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setName(registerRequest.getName());
        newUser.setRole(registerRequest.getRole().equalsIgnoreCase("DOCTOR") ? User.Role.DOCTOR : User.Role.PATIENT);
        userRepository.save(newUser);

        return ResponseEntity.status(201).body("User registered successfully!");
    }
}
