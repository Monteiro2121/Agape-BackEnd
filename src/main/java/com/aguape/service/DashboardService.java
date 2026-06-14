package com.aguape.service;

import com.aguape.dto.IndicadoresDTO;
import com.aguape.infra.repository.AbastecimentoRepository;
import com.aguape.infra.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final ViagemRepository viagemRepository;

    public DashboardService(AbastecimentoRepository abastecimentoRepository, ViagemRepository viagemRepository) {
        this.abastecimentoRepository = abastecimentoRepository;
        this.viagemRepository = viagemRepository;
    }

    public IndicadoresDTO buscarIndicadores(LocalDate dataInicio, LocalDate dataFim, Long veiculoId) {

        Double custoTotal = abastecimentoRepository.somarCustoTotalPeriodo(dataInicio, dataFim, veiculoId);
        Double kmTotal = viagemRepository.somarKmTotalPeriodo(dataInicio, dataFim, veiculoId);
        Long totalViagens = viagemRepository.contarViagensPeriodo(dataInicio, dataFim, veiculoId);

        System.out.println("DEBUG - KM Total vindo do banco: " + kmTotal);
        System.out.println("DEBUG - Total Viagens vindo do banco: " + totalViagens);

        custoTotal = (custoTotal != null) ? custoTotal : 0.0;
        kmTotal = (kmTotal != null) ? kmTotal : 0.0;
        int totalViagensInt = (totalViagens != null) ? totalViagens.intValue() : 0;

        Double custoMedioKm = 0.0;
        if (kmTotal > 0) {
            custoMedioKm = custoTotal / kmTotal;
        }

        return new IndicadoresDTO(custoMedioKm, kmTotal, totalViagensInt);
    }
}