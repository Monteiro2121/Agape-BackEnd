package com.aguape.application.service;


import com.aguape.infrastructure.adapters.in.web.dto.LoginRequestDTO;
import com.aguape.infrastructure.adapters.in.web.dto.LoginResponseDTO;
import com.aguape.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final com.agape.reportflow.infrastructure.persistence.UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        // Autentica o usuário usando o mecanismo do Spring Security
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtService.generateToken(user.getEmail());

        // Retorna o DTO com o Token, Nome e a Role (perfil) do usuário
        return new LoginResponseDTO(token, user.getName(), user.getRole().name());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}