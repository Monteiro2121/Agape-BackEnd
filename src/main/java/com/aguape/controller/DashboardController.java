package com.aguape.controller;

import com.aguape.dto.IndicadoresDTO;
import com.aguape.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/dashboard")
@Tag(name = "Dashboard", description = "Indicadores dinâmicos do AgFrota")
public class DashboardController {

    @Autowired
    private DashboardService service;
    //o primeiro endpoint q eu tinha feito
    @Operation(summary = "Busca indicadores principais", description = "Retorna consumo, KM e viagens filtrados")
    @GetMapping("/indicadores")
    public ResponseEntity<IndicadoresDTO> getIndicadores(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(service.buscarIndicadores(dataInicio, dataFim));
    }
}