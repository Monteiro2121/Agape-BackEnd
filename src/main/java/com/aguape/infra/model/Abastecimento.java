package com.aguape.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "abastecimentos") // Confirme se o nome da tabela no banco é este
@Getter
@Setter
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id") // Nome da coluna que liga ao veículo
    private Veiculo veiculo;

    private LocalDate data;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "valor_litro")
    private Double valorLitro;

    private String posto;
}