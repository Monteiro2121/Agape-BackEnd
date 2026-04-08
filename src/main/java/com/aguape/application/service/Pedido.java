package com.aguape.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private BigDecimal valor;

    // Se você ainda não tem o enum StatusPedido, mude para String temporariamente
    // private String status;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}