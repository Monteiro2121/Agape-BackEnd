package com.aguape.infra.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_via_viagem", schema = "agfrota")
public class Viagem {

    @Id
    @Column(name = "via_codigo")
    private Long id;

    @Column(name = "via_km_percorrido")
    private Double kmPercorrido;

    @Column(name = "via_dt_saida")
    private LocalDate dataSaida;

    // Construtores vazios são obrigatórios para o JPA
    public Viagem() {}

    // Getters e Setters (importante para o Hibernate ler os dados)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getKmPercorrido() { return kmPercorrido; }
    public void setKmPercorrido(Double kmPercorrido) { this.kmPercorrido = kmPercorrido; }

    public LocalDate getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }
}