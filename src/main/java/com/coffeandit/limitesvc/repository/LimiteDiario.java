package com.coffeandit.limitesvc.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "limite_diario")
@Data
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;
}
