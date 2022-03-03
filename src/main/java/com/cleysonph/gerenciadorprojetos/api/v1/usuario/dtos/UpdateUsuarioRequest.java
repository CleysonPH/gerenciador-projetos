package com.cleysonph.gerenciadorprojetos.api.v1.usuario.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.cleysonph.gerenciadorprojetos.api.v1.usuario.validators.UsuarioNonExistsByEmail;
import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.validators.Age;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsuarioRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nome;

    @Past
    @NotNull
    @Age(min = 18, max = 100)
    private LocalDate nascimento;

    @Email
    @NotNull
    @NotEmpty
    @UsuarioNonExistsByEmail
    private String email;

    @NotNull
    private Role role;

}
