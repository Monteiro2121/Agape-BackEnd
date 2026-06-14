package com.aguape.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConsumoMensalDTO {
    private String mes;
    private Double valorConsumo; // Mudei de 'valor' para 'valorConsumo' para bater com seu front

    public ConsumoMensalDTO(String mes, Double valorConsumo) {
        this.mes = mes;
        this.valorConsumo = valorConsumo;
    }
}