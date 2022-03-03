package com.cleysonph.gerenciadorprojetos.core.repositories;

import com.cleysonph.gerenciadorprojetos.core.exceptions.ProjetoNotFoundException;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

public interface ProjetoRepository extends
    JpaRepository<Projeto, Long>,
    JpaSpecificationExecutor<Projeto> {

    @EntityGraph(
        type = EntityGraphType.LOAD,
        attributePaths = {"lider", "responsavel"}
    )
    Page<Projeto> findAll(Specification<Projeto> spec, Pageable pageable);

    boolean existsByIdAndLiderEmail(Long id, String liderEmail);

    boolean existsByIdAndResponsavelEmail(Long id, String responsavelEmail);

    default Projeto findByIdOrElseThrow(Long id) {
        return this.findById(id)
            .orElseThrow(ProjetoNotFoundException::new);
    }

}
