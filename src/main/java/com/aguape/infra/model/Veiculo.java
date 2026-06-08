package com.aguape.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_vei_veiculo", schema = "agfrota")
@Getter
@Setter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vei_codigo") // Chave primária
    private Long id;

    @Column(name = "vei_placa")
    private String placa;

    @Column(name = "vei_situacao") // O "status" que você tinha é na verdade "vei_situacao"
    private String status;

    // Adicione outros campos se precisar, ex:
    // @Column(name = "vei_km_atual")
    // private Double kmAtual;
}