package com.aguape.infra.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusOperacaoConverter implements AttributeConverter<StatusOperacao, String> {

    @Override
    public String convertToDatabaseColumn(StatusOperacao status) {
        if (status == null) return null;
        return switch (status) {
            case ATIVO -> "A";
            case EM_MANUTENCAO -> "M";
            case INATIVO -> "I";
            case DISPONIVEL -> "D";
            default -> null;
        };
    }

    @Override
    public StatusOperacao convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;

        // O .toUpperCase() garante que "i" ou "I" funcionem
        return switch (dbData.toUpperCase()) {
            case "A" -> StatusOperacao.ATIVO;
            case "M" -> StatusOperacao.EM_MANUTENCAO;
            case "I" -> StatusOperacao.INATIVO;
            case "D" -> StatusOperacao.DISPONIVEL;
            case "V" -> StatusOperacao.ATIVO; // Mapeie o "V" para algo válido (ex: ATIVO)
            default -> null; // Em vez de erro, retorna null e o sistema não trava
        };
    }
}