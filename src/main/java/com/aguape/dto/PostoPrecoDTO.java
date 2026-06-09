package com.aguape.dto;

public class PostoPrecoDTO {
    private String nomePosto;
    private Double precoMedio;

    public PostoPrecoDTO(String nomePosto, Double precoMedio) {
        this.nomePosto = nomePosto;
        this.precoMedio = precoMedio;
    }

    // O Spring e o Jackson (que transforma em JSON) precisam disso:
    public String getNomePosto() {
        return nomePosto;
    }

    public Double getPrecoMedio() {
        return precoMedio;
    }
}