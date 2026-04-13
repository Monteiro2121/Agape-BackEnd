package com.aguape.application.service;

import com.aguape.domain.model.User;
import com.aguape.infrastructure.adapters.in.web.dto.LoginRequestDTO;
import com.aguape.infrastructure.adapters.in.web.dto.LoginResponseDTO;
import com.aguape.infrastructure.persistence.UserRepository; // IMPORT ADICIONADO
import com.aguape.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        // Agora o 'User' será o seu, não o do Apache
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDTO(token, user.getName(), user.getRole().name());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}