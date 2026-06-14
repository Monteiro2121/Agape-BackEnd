package com.aguape.service;

import com.aguape.dto.*;
import com.aguape.infra.model.StatusOperacao;
import com.aguape.infra.repository.AbastecimentoRepository;
import com.aguape.infra.repository.VeiculoRepository;
import com.aguape.infra.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PainelService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private AbastecimentoRepository abastecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    // 1. ERRO DO VEICULO RESOLVIDO: Puxa o findAll padrão e converte pro DTO
    public List<VeiculoFiltroDTO> buscarVeiculosSimplificados() {
        return veiculoRepository.findAll().stream()
                // Mude v.getId() e v.getPlaca() caso os nomes na sua entidade Veiculo sejam diferentes
                .map(v -> new VeiculoFiltroDTO(v.getId(), v.getPlaca()))
                .toList();
    }

    // 2. ERRO DO CONSTRUTOR RESOLVIDO: Passando os 5 parâmetros exatos que o DTO exige
    public ResumoPainelDTO gerarResumo(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        Double kmTotal = viagemRepository.somarKmTotalPeriodo(dataInicio, dataFim, veiculoId);
        Long totalViagensLong = viagemRepository.contarViagensPeriodo(dataInicio, dataFim, veiculoId);

        Integer totalViagens = (totalViagensLong != null) ? totalViagensLong.intValue() : 0;

        Double custoTotal = (veiculoId != null)
                ? abastecimentoRepository.somarCustoTotalPeriodo(dataInicio, dataFim, veiculoId)
                : abastecimentoRepository.somarCustoTotalPeriodo(dataInicio, dataFim);

        Double custoMedioKm = 0.0;
        if (kmTotal != null && kmTotal > 0) {
            custoMedioKm = custoTotal / kmTotal;
        }

        Map<String, Long> mapaVazio = new HashMap<>(); // Preenche o 4º parâmetro exigido
        Double valorZero = 0.0;                        // Preenche o 5º parâmetro exigido

        return new ResumoPainelDTO(custoMedioKm, kmTotal, totalViagens, mapaVazio, valorZero);
    }

    public List<ConsumoMensalDTO> buscarEvolucaoConsumo(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        List<Object[]> resultados = abastecimentoRepository.buscarConsumoMensal(veiculoId, dataInicio, dataFim);
        return resultados.stream().map(obj -> {
            String mes = obj[0] != null ? String.valueOf(obj[0]) : "";
            Double valor = obj[1] != null ? ((Number) obj[1]).doubleValue() : 0.0;
            return new ConsumoMensalDTO(mes, valor);
        }).toList();
    }

    public List<PostoPrecoDTO> buscarRankingPostos(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        List<Object[]> resultados = abastecimentoRepository.buscarMediaPrecosPostosNativo(veiculoId, dataInicio, dataFim);
        return resultados.stream().map(obj -> {
            String posto = obj[0] != null ? String.valueOf(obj[0]) : "Desconhecido";
            Double preco = obj[1] != null ? ((Number) obj[1]).doubleValue() : 0.0;
            return new PostoPrecoDTO(posto, preco);
        }).toList();
    }

    public List<QuilometragemDTO> buscarDadosQuilometragem(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        List<Object[]> resultados = viagemRepository.buscarQuilometragemGrafico(veiculoId, dataInicio, dataFim);
        return resultados.stream().map(obj -> {
            String mes = obj[0] != null ? String.valueOf(obj[0]) : "";
            Double kmEmpresa = obj[1] != null ? ((Number) obj[1]).doubleValue() : 0.0;
            Double kmTerc = obj[2] != null ? ((Number) obj[2]).doubleValue() : 0.0;
            return new QuilometragemDTO(mes, kmEmpresa, kmTerc);
        }).toList();
    }

    // 3. ERRO DA FUNCTIONAL INTERFACE RESOLVIDO: Removido o 'availability ->'
    public StatusFrotaDTO calcularStatusFrota(Long veiculoId, LocalDate dataInicio, LocalDate dataFim) {
        int operando = 0;
        int manutencao = 0;
        int parados = 0;

        if (veiculoId != null) {
            var v = veiculoRepository.findById(veiculoId).orElse(null);
            if (v != null) {
                if (v.getStatus() == StatusOperacao.ATIVO) operando = 1;
                else if (v.getStatus() == StatusOperacao.EM_MANUTENCAO) manutencao = 1;
                else if (v.getStatus() == StatusOperacao.INATIVO) parados = 1;
            }
        } else {
            Long op = veiculoRepository.countByStatus(StatusOperacao.ATIVO);
            Long man = veiculoRepository.countByStatus(StatusOperacao.EM_MANUTENCAO);
            Long par = veiculoRepository.countByStatus(StatusOperacao.INATIVO);

            operando = (op != null) ? op.intValue() : 0;
            manutencao = (man != null) ? man.intValue() : 0;
            parados = (par != null) ? par.intValue() : 0;
        }

        double disponibilidade = 0.0;
        int total = operando + manutencao + parados;
        if (total > 0) disponibilidade = ((double) operando / total) * 100;

        return new StatusFrotaDTO(operando, manutencao, parados, disponibilidade);
    }
}