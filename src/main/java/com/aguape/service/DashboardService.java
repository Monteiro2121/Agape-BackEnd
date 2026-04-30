package com.aguape.service;

import com.aguape.dto.IndicadoresDTO;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService {

    public IndicadoresDTO buscarIndicadores(LocalDate inicio, LocalDate fim) {


        return new IndicadoresDTO(
                new BigDecimal("1500.50"), // Média de Consumo (R$)
                450.0,                    // Total de KM
                12                        // Viagens no mês
        );
    }
}