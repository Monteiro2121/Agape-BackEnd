package com.aguape.infrastructure.adapters.in.web.dto;

import java.time.LocalDateTime;

// UserResponseDTO.java
public record UserResponseDTO(
        String id,
        String name,
        String email,
        String role,
        LocalDateTime createdAt
) {}
