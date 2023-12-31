package com.guilherme.vestidobank.vestidobank.model;

import com.guilherme.vestidobank.vestidobank.Enumeration.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

    @Column(name = "valor")
    private Double valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_trasacao")
    private Date dataTransacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType tipo;

}
