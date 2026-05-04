package com.aguape.service;

import com.aguape.dto.IndicadoresDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService {

    public IndicadoresDTO buscarIndicadores(LocalDate dataInicio, LocalDate dataFim) {

        // 🔥 TEMPORÁRIO (só pra funcionar)
        IndicadoresDTO dto = new IndicadoresDTO(
    BigDecimal.ZERO,
    0.0,
    0
);

        // aqui você poderia preencher com dados reais depois
        return dto;
    }
}