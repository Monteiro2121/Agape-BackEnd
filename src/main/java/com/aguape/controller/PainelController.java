package com.aguape.controller;

import java.time.LocalDate;
import java.util.List;

import com.aguape.dto.*;
import com.aguape.service.JasperService;
import com.aguape.service.PainelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    // Adicionado dataInicio e dataFim
    @GetMapping("/consumo-combustivel")
    public ResponseEntity<List<ConsumoMensalDTO>> buscarConsumoMensal(
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(painelService.buscarEvolucaoConsumo(veiculoId, dataInicio, dataFim));
    }

    // Adicionado dataInicio e dataFim
    @GetMapping("/quilometragem")
    public ResponseEntity<List<QuilometragemDTO>> buscarQuilometragem(
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(painelService.buscarDadosQuilometragem(veiculoId, dataInicio, dataFim));
    }

    // Adicionado dataInicio e dataFim
    @GetMapping("/status-veiculo")
    public ResponseEntity<StatusFrotaDTO> buscarStatusFrota(
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(painelService.calcularStatusFrota(veiculoId, dataInicio, dataFim));
    }

    // Adicionado veiculoId, dataInicio e dataFim para receber o filtro do front
    @GetMapping(value = "/postos-melhor-preco", produces = "application/json")
    public ResponseEntity<List<PostoPrecoDTO>> buscarPostosMelhorPreco(
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(painelService.buscarRankingPostos(veiculoId, dataInicio, dataFim));
    }

    @GetMapping("/indicadores")
    public ResponseEntity<ResumoPainelDTO> buscarIndicadores(
            @RequestParam(required = false) Long veiculoId, // <-- Recebe o ID do veículo vindo do front
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        return ResponseEntity.ok(painelService.gerarResumo(veiculoId, dataInicio, dataFim));
    }

    @Autowired
    private JasperService jasperService;

    @GetMapping("/dashboard/pdf")
    public ResponseEntity<byte[]> baixarPdfDashboard(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) Long veiculoId) {

        // 1. Coleta os mesmos dados exatos que o seu gráfico da tela usa
        ResumoPainelDTO resumo = painelService.gerarResumo(veiculoId, dataInicio, dataFim);
        StatusFrotaDTO status = painelService.calcularStatusFrota(veiculoId, dataInicio, dataFim);

        // 2. Passa os dados dinâmicos para o gerador do Jasper
        byte[] pdfBytes = jasperService.gerarRelatorioDashboard(dataInicio, dataFim, veiculoId, resumo, status);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio-principal.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}