package com.cleysonph.gerenciadorprojetos.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.cleysonph.gerenciadorprojetos.core.listeners.TarefaEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(TarefaEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tarefa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    private Projeto projeto;

    public boolean isConcluida() {
        return status.equals(Status.CONCLUIDA);
    }

    public enum Status {
        CRIADA, CONCLUIDA;
    }

}
