package com.aguape.infra.model; // Verifique se o nome da pasta é 'model' ou 'entity'

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculos") // ou o nome da tabela no banco da Ágape
@Getter @Setter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String status;
}