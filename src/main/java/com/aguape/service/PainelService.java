package com.aguape.service;

import com.aguape.dto.*;
import com.aguape.dto.ConsumoMensalDTO;
import org.springframework.stereotype.Service;
import com.aguape.dto.QuilometragemDTO;
import com.aguape.dto.StatusFrotaDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PainelService {

    public List<VeiculoFiltroDTO> buscarVeiculosSimplificados() {
        return new ArrayList<>();
    }

    public ResumoPainelDTO gerarResumo(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {

        return new ResumoPainelDTO(
                0.0,
                0.0,
                0,
                java.util.Map.of(),
                0.0
        );
    }


    public List<ConsumoMensalDTO> buscarEvolucaoConsumo(Long veiculoId) {
        return new ArrayList<>();
    }

    public List<PostoPrecoDTO> buscarRankingPostos() {
        return new ArrayList<>();
    }

    public List<QuilometragemDTO> buscarDadosQuilometragem(Long veiculoId) {
        return new ArrayList<>();
    }

    public StatusFrotaDTO calcularStatusFrota(Long veiculoId) {
        return new StatusFrotaDTO(0, 0, 0, 0.0);
    }
}