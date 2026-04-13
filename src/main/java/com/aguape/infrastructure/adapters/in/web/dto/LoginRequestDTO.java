package com.aguape.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {}






