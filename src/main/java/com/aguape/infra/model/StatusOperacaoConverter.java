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
        };
    }

    @Override
    public StatusOperacao convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;

        return switch (dbData.trim().toUpperCase()) {
            case "A", "V" -> StatusOperacao.ATIVO;
            case "M" -> StatusOperacao.EM_MANUTENCAO;
            case "I" -> StatusOperacao.INATIVO;
            case "D" -> StatusOperacao.DISPONIVEL;
            default -> null;
        };
    }
}