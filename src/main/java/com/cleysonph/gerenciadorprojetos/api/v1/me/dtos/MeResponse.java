package com.cleysonph.gerenciadorprojetos.api.v1.me.dtos;

import java.time.LocalDate;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.dtos.ResourceResponse;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;

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
public class MeResponse extends ResourceResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Status status;
    private String email;
    private Role role;

}
