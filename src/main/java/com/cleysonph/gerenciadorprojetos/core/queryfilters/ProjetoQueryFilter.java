package com.cleysonph.gerenciadorprojetos.core.queryfilters;

import static com.cleysonph.gerenciadorprojetos.core.specifications.ProjetoSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.*;

import java.time.LocalDate;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;

import org.springframework.data.jpa.domain.Specification;

public interface ProjetoQueryFilter extends QueryFilter<Projeto> {

    String getNome();
    String getDescricao();
    LocalDate getDataInicioGte();
    LocalDate getDataInicioLte();
    LocalDate getDataFimGte();
    LocalDate getDataFimLte();
    LocalDate getDataEntregaGte();
    LocalDate getDataEntregaLte();
    String getResponsavel();
    String getLider();
    List<Status> getStatus();

    @Override
    default Specification<Projeto> getSpec() {
        return where(withNomeContains(getNome())
            .and(withDescricaoContains(getDescricao()))
            .and(withDataInicioGreaterThanOrEqualTo(getDataInicioGte()))
            .and(withDataInicioLessThanOrEqualTo(getDataInicioLte()))
            .and(withDataFimGreaterThanOrEqualTo(getDataFimGte()))
            .and(withDataFimLessThanOrEqualTo(getDataFimLte()))
            .and(withDataEntregaGreaterThanOrEqualTo(getDataEntregaGte()))
            .and(withDataEntregaLessThanOrEqualTo(getDataEntregaLte()))
            .and(withResponsavelNomeContains(getResponsavel()))
            .and(withLiderNomeContains(getLider()))
            .and(withStatusIn(getStatus()))
        );
    }

}
