package com.cleysonph.gerenciadorprojetos.api.v1.usuario.queryfilters;

import java.time.LocalDate;
import java.util.List;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Status;
import com.cleysonph.gerenciadorprojetos.core.queryfilters.UsuarioQueryFilter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFindAllQueryFilter implements UsuarioQueryFilter {

    private String nome;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate nascimentoGte;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate nascimentoLte;

    private String email;

    private List<Role> role;

    private List<Status> status;

}
