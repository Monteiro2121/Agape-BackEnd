package com.aguape.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_aba_abastecimento", schema = "agfrota")
@Getter
@Setter
public class Abastecimento {
    @Id
    @Column(name = "aba_codigo")
    private Long id;

    @Column(name = "aba_quantidade")
    private Double quantidade;

    @Column(name = "aba_valor_pago")
    private Double valorPago;

    @Column(name = "aba_dt_abastecimento")
    private LocalDateTime dataAbastecimento;

    @Column(name = "pab_codigo")
    private Long pabCodigo; // ID do posto
}