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
    @Column(name = "vei_codigo")
    private Long id;

    @Column(name = "vei_placa")
    private String placa;

    // Apenas uma declaração para o status
    @Column(name = "vei_situacao")
    @Convert(converter = StatusOperacaoConverter.class)
    private StatusOperacao status;
}