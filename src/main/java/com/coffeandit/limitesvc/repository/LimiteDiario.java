package com.coffeandit.limitesvc.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

    @Id
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;
}
