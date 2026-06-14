package com.aguape.dto;

public class StatusFrotaDTO {
    private int operando;
    private int manutencao;
    private int parados;
    private double disponibilidade;

    // Construtor Padrão
    public StatusFrotaDTO() {
    }

    // Construtor completo que o PainelService usa
    public StatusFrotaDTO(int operando, int manutencao, int parados, double disponibilidade) {
        this.operando = operando;
        this.manutencao = manutencao;
        this.parados = parados;
        this.disponibilidade = disponibilidade;
    }

    // --- GETTERS EXPLICITOS PARA O JASPER ENCONTRAR ---

    public int getOperando() {
        return this.operando;
    }

    public int getManutencao() {
        return this.manutencao;
    }

    public int getParados() {
        return this.parados;
    }

    public double getDisponibilidade() {
        return this.disponibilidade;
    }

    // --- SETTERS (Caso o Spring ou Hibernate precisem) ---

    public void setOperando(int operando) {
        this.operando = operando;
    }

    public void setManutencao(int manutencao) {
        this.manutencao = manutencao;
    }

    public void setParados(int parados) {
        this.parados = parados;
    }

    public void setDisponibilidade(double disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}