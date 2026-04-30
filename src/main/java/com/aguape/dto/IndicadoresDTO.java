package com.aguape.dto;

import java.math.BigDecimal;

public record IndicadoresDTO(
        BigDecimal mediaConsumo,
        Double totalKmPercorridos,
        Integer viagensRealizadas
) {}

