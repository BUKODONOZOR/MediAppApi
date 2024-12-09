package com.empleabilidad.empleabilidad.Controllers;

import com.empleabilidad.empleabilidad.Dtos.LoginDTO;
import com.empleabilidad.empleabilidad.Dtos.LoginResponse;
import com.empleabilidad.empleabilidad.Dtos.RegisterDTO;
import com.empleabilidad.empleabilidad.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Login for users (both doctors and patients)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "403", description = "Invalid email or password")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(summary = "Register a new user (doctor or patient)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerRequest) {
        return authService.register(registerRequest);
    }
}
