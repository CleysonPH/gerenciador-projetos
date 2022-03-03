package com.cleysonph.gerenciadorprojetos.core.specifications;

import java.time.LocalDate;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario_;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;

import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecifications {

    public static Specification<Usuario> withNomeContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var nomeLowerCase = criteriaBuilder.lower(root.get(Usuario_.nome));
            return criteriaBuilder.like(nomeLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Usuario> withNascimentoGreaterThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Usuario_.nascimento), value);
        };
    }

    public static Specification<Usuario> withNascimentoLessThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Usuario_.nascimento), value);
        };
    }

    public static Specification<Usuario> withEmailContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var emailLowerCase = criteriaBuilder.lower(root.get(Usuario_.email));
            return criteriaBuilder.like(emailLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Usuario> withRoleIn(List<Role> value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Usuario_.role).in(value);
        };
    }

    public static Specification<Usuario> withStatusIn(List<Status> value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Usuario_.status).in(value);
        };
    }

}
