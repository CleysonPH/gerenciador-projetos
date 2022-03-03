package com.cleysonph.gerenciadorprojetos.core.repositories;

import java.util.Optional;

import com.cleysonph.gerenciadorprojetos.core.exceptions.UsuarioNotFoundException;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends
    JpaRepository<Usuario, Long>,
    JpaSpecificationExecutor<Usuario> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndId(String email, Long id);

    boolean existsByIdAndRole(Long value, Role usuario);

    Optional<Usuario> findByEmail(String email);

    default Usuario findByIdOrElseThrow(Long id) {
        return this.findById(id)
            .orElseThrow(UsuarioNotFoundException::new);
    }

}
