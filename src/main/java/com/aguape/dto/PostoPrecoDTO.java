package com.aguape.dto;

public record PostoPrecoDTO(
        Long postoId, // Mudei de String para Long
        Double precoMedio
) {}