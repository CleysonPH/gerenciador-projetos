package com.cleysonph.gerenciadorprojetos.core.queryfilters;

import static com.cleysonph.gerenciadorprojetos.core.specifications.UsuarioSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.*;

import java.time.LocalDate;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;

import org.springframework.data.jpa.domain.Specification;

public interface UsuarioQueryFilter extends QueryFilter<Usuario> {

    String getNome();
    LocalDate getNascimentoGte();
    LocalDate getNascimentoLte();
    String getEmail();
    List<Role> getRole();
    List<Status> getStatus();

    @Override
    default Specification<Usuario> getSpec() {
        return where(withNomeContains(getNome())
            .and(withNascimentoGreaterThanOrEqualTo(getNascimentoGte()))
            .and(withNascimentoLessThanOrEqualTo(getNascimentoLte()))
            .and(withEmailContains(getEmail()))
            .and(withRoleIn(getRole()))
            .and(withStatusIn(getStatus()))
        );
    }

}
