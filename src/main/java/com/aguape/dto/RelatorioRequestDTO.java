package com.aguape.dto;

import lombok.Data;

@Data
public class RelatorioRequestDTO {
    private String periodo;
    private Long veiculoId;
    private String dataInicio;
    private String dataFim;
}