package com.cleysonph.gerenciadorprojetos.core.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.cleysonph.gerenciadorprojetos.core.listeners.ProjetoEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(ProjetoEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Projeto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ToString.Exclude
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    private LocalDate dataEntrega;

    @ManyToOne(optional = false)
    private Usuario responsavel;

    @ManyToOne(optional = false)
    private Usuario lider;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public boolean isAceito() {
        return status.equals(Status.ACEITO);
    }

    public enum Status {
        CRIADO, ACEITO, CONCLUIDO, CONCLUIDO_COM_ATRASO;
    }
}
