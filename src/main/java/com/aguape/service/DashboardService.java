package com.aguape.service;

import com.aguape.dto.IndicadoresDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    public IndicadoresDTO buscarIndicadores(LocalDate dataInicio, LocalDate dataFim) {

        // 🔥 TEMPORÁRIO (só pra funcionar)
        IndicadoresDTO dto = new IndicadoresDTO();

        // aqui você poderia preencher com dados reais depois
        return dto;
    }
}