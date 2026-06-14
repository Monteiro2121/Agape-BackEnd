package com.aguape.dto;

import java.util.Map;

public class ResumoPainelDTO {
    private Double custoMedioPorKm;
    private Double kmTotal;
    private Integer totalViagens;
    private Map<String, Long> status;
    private Double disponibilidade;

    public ResumoPainelDTO(Double custoMedioPorKm, Double kmTotal, Integer totalViagens,
                           Map<String, Long> status, Double disponibilidade) {
        this.custoMedioPorKm = custoMedioPorKm;
        this.kmTotal = kmTotal;
        this.totalViagens = totalViagens;
        this.status = status;
        this.disponibilidade = disponibilidade;
    }

    // Seu getter que já existia
    public Double getCustoMedioKm() {
        return this.custoMedioPorKm;
    }

    // --- ADICIONE ESSES GETTERS ABAIXO PARA MATAR O ERRO DE COMPILAÇÃO ---

    public Double getKmTotal() {
        return this.kmTotal;
    }

    public Integer getTotalViagens() {
        return this.totalViagens;
    }

    public Map<String, Long> getStatus() {
        return this.status;
    }

    public Double getDisponibilidade() {
        return this.disponibilidade;
    }
}