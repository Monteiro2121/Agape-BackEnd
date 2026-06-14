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

    public Double getCustoMedioKm() {
        return this.custoMedioPorKm;
    }


}