package com.guilherme.vestidobank.vestidobank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "primeiro_nome", nullable = false, length = 75)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 75)
    private String sobrenome;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @OneToMany(mappedBy = "usuario",
            targetEntity = Transaction.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transacoes;

    @OneToOne
    @JoinColumn(name = "conta")
    private Account conta;
}