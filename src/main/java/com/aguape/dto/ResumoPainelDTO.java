package com.aguape.dto;

public record ResumoPainelDTO(
        Double custoMedioPorKm,
        Double kmTotal,
        Integer viagensRealizadas,
        java.util.Map<String, Long> statusFrota,
        Double percentualDisponibilidade
) {}
