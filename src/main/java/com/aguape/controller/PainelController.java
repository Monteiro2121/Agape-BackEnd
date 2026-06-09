package com.aguape.controller;
import java.time.LocalDate;
import java.util.List;

import com.aguape.dto.*;
import com.aguape.service.PainelService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/painel")
public class PainelController {

    private final PainelService painelService;

    public PainelController(PainelService painelService) {
        this.painelService = painelService;
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<VeiculoFiltroDTO>> listarVeiculosParaFiltro() {
        return ResponseEntity.ok(painelService.buscarVeiculosSimplificados());
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoPainelDTO> buscarResumo(
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        ResumoPainelDTO resumo = painelService.gerarResumo(veiculoId, dataInicio, dataFim);
        return ResponseEntity.ok(resumo);
    }

    @GetMapping("/consumo-combustivel")
    public ResponseEntity<List<ConsumoMensalDTO>> buscarConsumoMensal(
            @RequestParam(required = false) Long veiculoId) {
        return ResponseEntity.ok(painelService.buscarEvolucaoConsumo(veiculoId));
    }

    @GetMapping("/quilometragem")
    public ResponseEntity<List<QuilometragemDTO>> buscarQuilometragem(
            @RequestParam(required = false) Long veiculoId) {
        return ResponseEntity.ok(painelService.buscarDadosQuilometragem(veiculoId));
    }

    @GetMapping("/status-veiculo")
    public ResponseEntity<StatusFrotaDTO> buscarStatusFrota(
            @RequestParam(required = false) Long veiculoId) {
        return ResponseEntity.ok(painelService.calcularStatusFrota(veiculoId));
    }

    @GetMapping(value = "/postos-melhor-preco", produces = "application/json")
    public ResponseEntity<List<PostoPrecoDTO>> buscarPostosMelhorPreco() {
        return ResponseEntity.ok(painelService.buscarRankingPostos());
    }
}