package com.empleabilidad.empleabilidad.Services;

import com.empleabilidad.empleabilidad.Models.User;
import com.empleabilidad.empleabilidad.Repositories.UserRepository;
import com.empleabilidad.empleabilidad.Utils.CustomDatails.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor que inyecta el repositorio de User
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar al usuario por email
        Optional<User> userOptional = userRepository.findByEmail(email);

        // Si el usuario no se encuentra, lanzar una excepción
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Obtener el usuario
        User user = userOptional.get();

        // Asignar el rol basado en el atributo 'role' de User
        String role = "ROLE_" + user.getRole().name(); // 'ROLE_DOCTOR' o 'ROLE_PATIENT'

        // Retornar un CustomUserDetails con el email, nombre y rol
        return new CustomUserDetails(user.getEmail(), user.getName(), role);
    }
}
