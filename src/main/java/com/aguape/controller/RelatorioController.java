package com.aguape.controller;

import com.aguape.service.JasperService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final JasperService jasperService;

    public RelatorioController(JasperService jasperService) {
        this.jasperService = jasperService;
    }

    @GetMapping("/certidao")
    public ResponseEntity<byte[]> baixarCertidao() {
        // 1. Chamamos o serviço para gerar o array de bytes do PDF
        byte[] relatorio = jasperService.gerarCertidaoPositiva();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDispositionFormData("inline", "certidao.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(relatorio);
    }
}