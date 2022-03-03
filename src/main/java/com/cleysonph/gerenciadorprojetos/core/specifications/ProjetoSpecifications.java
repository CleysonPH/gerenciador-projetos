package com.cleysonph.gerenciadorprojetos.core.specifications;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.JoinType;

import com.cleysonph.gerenciadorprojetos.core.models.Projeto;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto.Status;
import com.cleysonph.gerenciadorprojetos.core.models.Projeto_;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario_;

import org.springframework.data.jpa.domain.Specification;

public class ProjetoSpecifications {

    public static Specification<Projeto> withNomeContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var nomeLowerCase = criteriaBuilder.lower(root.get(Projeto_.nome));
            return criteriaBuilder.like(nomeLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Projeto> withDescricaoContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var descricaoLowerCase = criteriaBuilder.lower(root.get(Projeto_.descricao));
            return criteriaBuilder.like(descricaoLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Projeto> withDataInicioGreaterThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Projeto_.dataInicio), value);
        };
    }

    public static Specification<Projeto> withDataInicioLessThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Projeto_.dataInicio), value);
        };
    }

    public static Specification<Projeto> withDataFimGreaterThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Projeto_.dataFim), value);
        };
    }

    public static Specification<Projeto> withDataFimLessThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Projeto_.dataFim), value);
        };
    }

    public static Specification<Projeto> withDataEntregaGreaterThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Projeto_.dataEntrega), value);
        };
    }

    public static Specification<Projeto> withDataEntregaLessThanOrEqualTo(LocalDate value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Projeto_.dataEntrega), value);
        };
    }

    public static Specification<Projeto> withResponsavelNomeContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var responsavelJoin = root.join(Projeto_.responsavel, JoinType.LEFT);
            var responsavelNomeLowerCase = criteriaBuilder.lower(responsavelJoin.get(Usuario_.nome));
            return criteriaBuilder.like(responsavelNomeLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Projeto> withLiderNomeContains(String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            var liderJoin = root.join(Projeto_.lider, JoinType.LEFT);
            var liderNomeLowerCase = criteriaBuilder.lower(liderJoin.get(Usuario_.nome));
            return criteriaBuilder.like(liderNomeLowerCase, "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Projeto> withStatusIn(List<Status> value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Projeto_.status).in(value);
        };
    }

    public static Specification<Projeto> withLider(Usuario value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.equal(root.get(Projeto_.lider), value);
        };
    }

    public static Specification<Projeto> withResponsavel(Usuario value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.and();
            }
            return criteriaBuilder.equal(root.get(Projeto_.responsavel), value);
        };
    }

}
