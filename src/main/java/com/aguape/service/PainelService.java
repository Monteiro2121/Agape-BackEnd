package com.aguape.service;

import com.aguape.dto.*;

import com.aguape.infra.repository.AbastecimentoRepository;
import com.aguape.infra.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PainelService {

    private final VeiculoRepository veiculoRepository;
    private final AbastecimentoRepository abastecimentoRepository;

    // Injeção via construtor
    public PainelService(VeiculoRepository veiculoRepository, AbastecimentoRepository abastecimentoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.abastecimentoRepository = abastecimentoRepository;
    }

    public List<VeiculoFiltroDTO> buscarVeiculosSimplificados() {
        return veiculoRepository.findAll().stream()
                .map(v -> new VeiculoFiltroDTO(v.getId(), v.getPlaca()))
                .toList();
    }

    public ResumoPainelDTO gerarResumo(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        return new ResumoPainelDTO(
                4.85,
                5420.0,  // KM Total
                124,     // Viagens
                Map.of("Operando", veiculoRepository.countByStatus("OPERANDO")),
                92.5     // Disponibilidade
        );
    }

    public List<ConsumoMensalDTO> buscarEvolucaoConsumo(Long veiculoId) {
        List<Object[]> resultados = abastecimentoRepository.buscarConsumoMensal(veiculoId);
        return resultados.stream()
                .map(obj -> new ConsumoMensalDTO((String) obj[0], ((Number) obj[1]).doubleValue()))
                .toList();
    }

    public List<PostoPrecoDTO> buscarRankingPostos() {
        List<Object[]> resultados = abastecimentoRepository.buscarMediaPrecosPostosNativo();

        return resultados.stream()
                .map(obj -> {
                    Long postoId = ((Number) obj[0]).longValue();
                    Double media = ((Number) obj[1]).doubleValue();
                    return new PostoPrecoDTO(postoId, media); // AQUI você chama o construtor real
                })
                .toList();
    }

    public List<QuilometragemDTO> buscarDadosQuilometragem(Long veiculoId) {
        return List.of(
                new QuilometragemDTO("Jan", 1200.0, 400.0),
                new QuilometragemDTO("Fev", 1500.0, 350.0)
        );
    }

    public StatusFrotaDTO calcularStatusFrota(Long veiculoId) {
        int operando = (int) veiculoRepository.countByStatus("OPERANDO");
        int manutencao = (int) veiculoRepository.countByStatus("MANUTENCAO");
        int parados = (int) veiculoRepository.countByStatus("PARADO");

        double disponibilidade = 0.0;
        int total = operando + manutencao + parados;
        if (total > 0) disponibilidade = ((double) operando / total) * 100;

        return new StatusFrotaDTO(operando, manutencao, parados, disponibilidade);
    }
}