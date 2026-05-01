package com.aguape.controller;
import com.aguape.dto.IndicadoresDTO;
import com.aguape.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

// 👇 IMPORTS NOVOS
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/v1/dashboard")
@Tag(name = "Dashboard", description = "Indicadores dinâmicos do AgFrota")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @Operation(summary = "Busca indicadores principais", description = "Retorna consumo, KM e viagens filtrados")
    @GetMapping("/indicadores")
    public ResponseEntity<?> getIndicadores(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {

        // 🔐 PEGA USUÁRIO LOGADO
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 🔐 VERIFICA PERFIL
        if (auth == null || auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("AgFrota"))) {

            return ResponseEntity.status(403).body("Acesso negado");
        }

        // ✅ LIBERADO
        return ResponseEntity.ok(service.buscarIndicadores(dataInicio, dataFim));
    }
}