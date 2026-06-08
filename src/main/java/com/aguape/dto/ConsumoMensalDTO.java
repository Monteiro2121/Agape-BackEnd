package com.aguape.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConsumoMensalDTO {
    private String mes;
    private Double valor;

    // ESTE CONSTRUTOR É OBRIGATÓRIO E DEVE SER PÚBLICO
    public ConsumoMensalDTO(String mes, Double valor) {
        this.mes = mes;
        this.valor = valor;
    }
}