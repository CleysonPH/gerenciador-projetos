package com.cleysonph.gerenciadorprojetos.core.repositories;

import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.exceptions.TarefaNotFoundException;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa;
import com.cleysonph.gerenciadorprojetos.core.models.Tarefa.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByProjeto(Projeto projeto);

    boolean existsByIdAndProjetoLiderEmail(Long id, String projetoLiderEmail);

    boolean existsByIdAndProjetoResponsavelEmail(Long id, String projetoResponsavelEmail);

    boolean existsByProjetoAndStatus(Projeto projeto, Status status);

    default Tarefa findByIdOrElseThrow(Long id) {
        return findById(id)
            .orElseThrow(TarefaNotFoundException::new);
    }

}
