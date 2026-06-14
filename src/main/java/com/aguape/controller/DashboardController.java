package com.aguape.controller;

import com.aguape.dto.IndicadoresDTO;
import com.aguape.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Indicadores dinâmicos do AgFrota")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/indicadores")
    public ResponseEntity<IndicadoresDTO> getIndicadores(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim,
            @RequestParam(required = false) Long veiculoId) {
        System.out.println("DEBUG: Rota /indicadores chamada com: " + dataInicio +
                " até " + dataFim + " | veiculoId: " + veiculoId);
        return ResponseEntity.ok(service.buscarIndicadores(dataInicio, dataFim, veiculoId));
    }
}