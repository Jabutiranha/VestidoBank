package com.guilherme.vestidobank.vestidobank.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "saque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private User usuario;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_saque")
    private Date dataSaque;

    @Column(name = "meio_pagamento", nullable = false, length = 75)
    private String meioPagamento;
}
