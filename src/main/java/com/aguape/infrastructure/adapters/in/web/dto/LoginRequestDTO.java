package com.aguape.infrastructure.adapters.in.web.dto;

import java.time.LocalDateTime;

public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {}


// LoginResponseDTO.java
public record LoginResponseDTO(
        String token,
        String name,
        String role
) {}


// UserResponseDTO.java
public record UserResponseDTO(
        String id,
        String name,
        String email,
        String role,
        LocalDateTime createdAt
) {}
