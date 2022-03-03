package com.cleysonph.gerenciadorprojetos.api.v1.me.dtos;

import java.time.LocalDate;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Status status;
    private String email;
    private Role role;

}
