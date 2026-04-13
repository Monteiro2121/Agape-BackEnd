package com.aguape.infrastructure.adapters.in.web.dto;

// LoginResponseDTO.java
public record LoginResponseDTO(
        String token,
        String name,
        String role
) {}
