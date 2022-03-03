package com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos;

import java.time.LocalDate;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioResponse extends ResourceResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Status status;
    private String email;
    private Role role;

    @JsonIgnore
    public boolean isAtivo() {
        return status.equals(Status.ATIVO);
    }

    @JsonIgnore
    public boolean isInativo() {
        return status.equals(Status.INATIVO);
    }

}
