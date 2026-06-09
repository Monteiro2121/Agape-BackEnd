package com.aguape.infra.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusOperacao {
    ATIVO,
    EM_MANUTENCAO,
    INATIVO,
    DISPONIVEL;

    // Isso ajuda a evitar o erro ao converter do banco/JSON
    @JsonCreator
    public static StatusOperacao fromString(String value) {
        if (value == null) return null;
        switch (value.toUpperCase()) {
            case "I": return INATIVO;
            case "D": return DISPONIVEL;
            default:
                try {
                    return StatusOperacao.valueOf(value.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return null; // Ou lance uma exceção mais amigável
                }
        }
    }
}