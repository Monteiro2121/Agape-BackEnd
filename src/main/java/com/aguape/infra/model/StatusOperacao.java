package com.aguape.infra.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusOperacao {
    ATIVO,
    EM_MANUTENCAO,
    INATIVO,
    DISPONIVEL;

    @JsonCreator
    public static StatusOperacao fromString(String value) {
        if (value == null || value.isBlank()) return null;

        switch (value.trim().toUpperCase()) {
            case "A": return ATIVO;
            case "M": return EM_MANUTENCAO;
            case "I": return INATIVO;
            case "D": return DISPONIVEL;
            default:
                try {
                    return StatusOperacao.valueOf(value.trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    return null;
                }
        }
    }
}