package com.cleysonph.gerenciadorprojetos.core.queryfilters;

import org.springframework.data.jpa.domain.Specification;

public interface QueryFilter<M> {

    Specification<M> getSpec();

}
