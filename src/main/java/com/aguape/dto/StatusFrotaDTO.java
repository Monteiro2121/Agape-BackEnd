package com.aguape.dto;

public record StatusFrotaDTO(
        Integer operando,
        Integer manutencao,
        Integer parados,
        Double percentualDisponibilidade
) {}
